package com.xi.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.*;
import com.xi.config.oss.AliyunOssConfig;
import com.xi.service.FileService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Resource
    OSS ossClient;

    @Resource
    AliyunOssConfig aliyunOssConfig;

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

    public void simpleManyUpload(MultipartFile[] files) {
        String parentDir = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        try {
            for (MultipartFile file : files) {
                System.out.println();
                String filetype=file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                String filename = UUID.randomUUID().toString();

                PutObjectRequest putObjectRequest = new PutObjectRequest(aliyunOssConfig.getBucketName(), aliyunOssConfig.getDir() + "/" + parentDir + "/" + filename+filetype, file.getInputStream());

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

                PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
                System.out.println(putObjectResult.getResponse().getContent());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
