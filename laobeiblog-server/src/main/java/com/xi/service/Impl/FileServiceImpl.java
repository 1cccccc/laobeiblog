package com.xi.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.config.oss.AliyunOssConfig;
import com.xi.entity.FileEntity;
import com.xi.mapper.FileMapper;
import com.xi.service.FileService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileEntity> implements FileService {
    @Resource
    OSS ossClient;

    @Resource
    AliyunOssConfig aliyunOssConfig;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    ThreadPoolExecutor threadPoolExecutor;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //oss policy文件直传
    @Override
    public Map<String, String> policy() {
        Map<String, String> respMap = new LinkedHashMap<>();
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, aliyunOssConfig.getDir());

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap = new LinkedHashMap<String, String>();
            respMap.put("accessId", aliyunOssConfig.getAccessKeyId());
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", aliyunOssConfig.getDir());
            respMap.put("host", aliyunOssConfig.getHost());
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));

//            JSONObject jasonCallback = new JSONObject();
//            jasonCallback.put("callbackUrl", callbackUrl);
//            jasonCallback.put("callbackBody",
//                    "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
//            jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
//            String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
//            respMap.put("callback", base64CallbackBody);

//            JSONObject ja1 = JSONObject.fromObject(respMap);
//            // System.out.println(ja1.toString());
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "GET, POST");
//            response(request, response, ja1.toString());

        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return respMap;
    }

    public List<Map<String, String>> simpleManyUpload(int userId, MultipartFile[] files) {
        String parentDir = dateFormat.format(new Date());
        List<Map<String, String>> list = new LinkedList<>();
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

        try {
            for (MultipartFile file : files) {
                //上传到oss的文件名
                String filetype = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                String filename = UUID.randomUUID().toString();
                String path = aliyunOssConfig.getDir() + "/" + parentDir + "/" + userId + "_" + filename + filetype;

                //计算文件内容md5值用于秒传
                String md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
                String key = operations.get(md5);

                if (StringUtils.isEmpty(key)) {//如果redis中没有信息表示这个文件没有被上传
                    CompletableFuture.supplyAsync(() -> {
                        try {
                            PutObjectRequest putObjectRequest = new PutObjectRequest(aliyunOssConfig.getBucketName(), path, file.getInputStream());
                            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);

                            return true;
                        } catch (IOException e) {
                            return false;
                        }
                    }, threadPoolExecutor).thenAcceptAsync(result -> {
                        if (result) {
                            operations.set(md5, path);

                            try {
                                this.save(new FileEntity().setFileMd5(md5).setUserId(userId).setOssUrl(aliyunOssConfig.getHost()+"/"+path).setUploadTime(dateFormat.parse(dateFormat.format(new Date()))));
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }, threadPoolExecutor);

                    String[] split = path.split("/");

                    Map<String, String> map = new HashMap<>();
                    map.put("filename", split[split.length-1]);
                    map.put("requestUrl", aliyunOssConfig.getHost()+"/"+path);
                    list.add(map);
                }else{
                    String[] split = key.split("/");

                    Map<String, String> map = new HashMap<>();
                    map.put("filename", split[split.length-1]);
                    map.put("requestUrl", aliyunOssConfig.getHost()+"/"+key);
                    list.add(map);
                }




                //设置回调，需要公网ip
//                Callback callback = new Callback();
//                callback.setCallbackUrl(aliyunOssConfig.getCallbackUrl());
//                //callback.setCallbackHost("192.168.1.4");
//                // 设置发起回调时请求body的值。
//                callback.setCallbackBody("{\\\"mimeType\\\":${mimeType},\\\"size\\\":${size}}");
//                // 设置发起回调请求的Content-Type。
//                callback.setCalbackBodyType(Callback.CalbackBodyType.JSON);
//                callback.addCallbackVar("x:var1", "value1");
//                callback.addCallbackVar("x:var2", "value2");
//                putObjectRequest.setCallback(callback);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public List<List<OSSObjectSummary>> getFileList(int userId) {
        List<FileEntity> list = this.list(new QueryWrapper<FileEntity>().eq("user_id", userId));

        List<List<OSSObjectSummary>> collect = list.stream().distinct().map(entity -> {
            String date = dateFormat.format(entity.getUploadTime());
            String prefix=aliyunOssConfig.getDir() + "/" + date + "/" + userId + "_";

            ObjectListing objects = ossClient.listObjects(aliyunOssConfig.getBucketName(), prefix);

            return objects.getObjectSummaries();
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public boolean removeFile(String md5) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String key = operations.get(md5);
        if(key.isEmpty()){
            return false;
        }

        ossClient.deleteObject(aliyunOssConfig.getBucketName(),aliyunOssConfig.getHost()+"/"+key);
        CompletableFuture.runAsync(()->{
            stringRedisTemplate.delete(md5);

            this.remove(new QueryWrapper<FileEntity>().eq("file_md5",md5));
        },threadPoolExecutor);
        
        return true;
    }
}
