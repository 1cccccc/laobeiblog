package com.xi.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class CodeUtilsProperties {
    @Value("${code.host}")
    private String host;//主机
    @Value("${code.path}")
    private String path;//路径
    @Value("${code.method}")
    private String method;//请求方式
    @Value("${code.appcode}")
    private String appcode;//你的appcode
    @Value("${code.templateId}")
    private String templateId;
    @Value("${spring.mail.username}")
    private String from;
}
