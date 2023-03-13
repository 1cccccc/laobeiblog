package com.xi.service;

import com.aliyun.oss.model.OSSObjectSummary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileService extends IService<FileEntity> {
    public Map<String,String> policy();

    public List<Map<String, String>> simpleManyUpload(int userId, MultipartFile[] files);

    public List<FileEntity> getFileList(int userId);

    public List<Boolean> removeFiles(String[] md5s);
}
