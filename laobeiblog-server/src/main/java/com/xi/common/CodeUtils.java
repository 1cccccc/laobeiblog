package com.xi.common;

import com.xi.entity.ToEmail;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.http.HttpResponse;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CodeUtils {

    @Resource
    private CodeUtilsProperties codeUtilsProperties;

    @Resource
    private JavaMailSender mailSender;

    public void sendCode(String phone, String code){
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + codeUtilsProperties.getAppcode());
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:"+code);
        bodys.put("phone_number", phone);
        bodys.put("template_id", codeUtilsProperties.getTemplateId());


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(codeUtilsProperties.getHost(), codeUtilsProperties.getPath(), codeUtilsProperties.getMethod(), headers, querys, bodys);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEmailCode(ToEmail to){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(codeUtilsProperties.getFrom());
            helper.setSubject(to.getSubject());
            helper.setTo(to.getTos());


            String content=Constant.HTMLPREFIX.getValue()+to.getContent()+Constant.HTMLSUFFIX.getValue();
            helper.setText(content,true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
