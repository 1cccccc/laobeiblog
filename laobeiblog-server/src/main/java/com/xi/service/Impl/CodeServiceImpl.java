package com.xi.service.Impl;

import com.xi.common.CodeUtils;
import com.xi.common.Constant;
import com.xi.common.QRCodeUtil;
import com.xi.entity.ToEmail;
import com.xi.service.CodeService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class CodeServiceImpl implements CodeService {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    ThreadPoolExecutor threadPoolExecutor;

    @Resource
    JavaMailSender javaMailSender;

    @Resource
    CodeUtils codeUtils;

    @Override
    public void sendCode(String phone) {
        //发送验证码前需要判断该手机号是否已经发过验证码了
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String phoneCode = operations.get(Constant.CODEPREFIX.getValue() + phone);
        if (!StringUtils.isBlank(phoneCode)) {
            CompletableFuture.runAsync(() -> {
                codeUtils.sendCode(phone, phoneCode);
            }, threadPoolExecutor);

            return ;
        }

        CompletableFuture.runAsync(() -> {
            String s = UUID.randomUUID().toString();
            String substring = s.substring(s.length() - 7, s.length() - 1);
            codeUtils.sendCode(phone, substring);

            //将验证码放入redis
            operations.set(Constant.CODEPREFIX.getValue() + phone, substring,5, TimeUnit.MINUTES);
        }, threadPoolExecutor);
    }


    @Override
    public void sendEmailCode(String email) {

        ToEmail toEmail = new ToEmail();
        String[] tos = {email};
        toEmail.setTos(tos);
        toEmail.setSubject("验证码");

        //判断是否已经发过了
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String phoneCode = operations.get(Constant.EMAILPREFIX.getValue() + email);
        if (!StringUtils.isBlank(phoneCode)){//已经发送过
            CompletableFuture.runAsync(()->{
                toEmail.setContent(phoneCode);
                codeUtils.sendEmailCode(toEmail);
            },threadPoolExecutor);

            return ;
        }

        //没有发送过

        CompletableFuture.runAsync(()->{
            String s = UUID.randomUUID().toString();
            String substring = s.substring(s.length() - 7, s.length() - 1);
            toEmail.setContent(substring);
            codeUtils.sendEmailCode(toEmail);

            operations.set(Constant.EMAILPREFIX.getValue()+email,substring,5, TimeUnit.MINUTES);
        },threadPoolExecutor);
    }

    @Override
    public String getQRCode(String content) {
        String qrCode = QRCodeUtil.getBase64QRCode(content);

        return qrCode;
    }
}
