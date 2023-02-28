package com.xi.config.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AliyunOssConfig {
    @Value(("${aliyun.endpoint}"))
    private String endpoint;
    @Value(("${aliyun.accessKeyId}"))
    private String accessKeyId;
    @Value(("${aliyun.accessKeySecret}"))
    private String accessKeySecret;
    @Value(("${aliyun.bucketName}"))
    private String bucketName;
    @Value("${aliyun.host}")
    private String host;
    @Value("${aliyun.callbackUrl:''}")
    private String callbackUrl;
    @Value("${aliyun.dir}")
    private String dir;


    @Bean
    public OSS ossClient(){
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
