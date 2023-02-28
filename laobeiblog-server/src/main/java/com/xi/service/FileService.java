package com.xi.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileService {
    public Map<String,String> policy();

    public void simpleManyUpload(MultipartFile[] files);
}
