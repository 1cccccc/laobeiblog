package com.xi.controller;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.xi.common.Result;
import com.xi.common.RsaUtils;
import com.xi.security.JwtUtils;
import com.xi.swagger.api.KeyApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/key")
public class KeyController implements KeyApi {
    private final RSAKey rsaKey= JwtUtils.loadJKSByClassPath();

    @Override
    @GetMapping("/getPublicKey")
    public Result getPublicKey() {
        String publicKey =null;
        try {
            publicKey = RsaUtils.getPublicKey(rsaKey);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return Result.success().setData(publicKey);
    }
}
