package com.xi.service;

public interface CodeService {
    public void sendCode(String phone) ;

    public void sendEmailCode(String email) ;

    String getQRCode(String content);
}
