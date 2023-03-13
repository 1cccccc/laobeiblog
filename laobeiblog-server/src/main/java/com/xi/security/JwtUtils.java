package com.xi.security;

import com.alibaba.fastjson2.JSON;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.xi.entity.UserEntity;
import com.xi.exception.JwtInvalidException;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

import static com.nimbusds.jose.JWSAlgorithm.RS256;

public class JwtUtils {
    /**
     * 加载证书文件和RSA密钥对
     * @return RSA密钥对
     */
    public static RSAKey loadJKSByClassPath(){
        try {
            //获取文件流
            InputStream jwtJKS =  new ClassPathResource("/keys/jwt.jks").getInputStream();

            //文件库的类型
            KeyStore keyStore = KeyStore.getInstance("JKS");

            //加载文件
            keyStore.load(jwtJKS,"000000".toCharArray());

            //获取证书文件中的公钥和私钥构建一个密钥对
            RSAPublicKey publicKey = (RSAPublicKey) keyStore.getCertificate("jwt").getPublicKey();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyStore.getKey("jwt", "000000".toCharArray());
            RSAKey rsaKey = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
            return rsaKey;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用RSA私钥进行生成token
     * @param payloads 载荷信息
     * @param rsaKey RSA密钥对
     * @return token 序列化后的JWS
     * @throws JOSEException
     */
    public static String generateTokenByRSA(Object payloads, RSAKey rsaKey) throws JOSEException {
        //构建JWS头部信息
        JWSHeader jwsHeader = new JWSHeader.Builder(RS256).type(JOSEObjectType.JWT).build();

        String string = JSON.toJSONString(payloads);
        //创建JWS的载荷信息，需要是一个JSON字符串
        Payload payload = new Payload(string);

        //组建JWS
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        //创建一个签名器
        RSASSASigner rsassaSigner = new RSASSASigner(rsaKey, true);

        //签名
        jwsObject.sign(rsassaSigner);
        return jwsObject.serialize();
    }

    /**
     * 使用RSA公钥进行token解析
     * @param token
     * @param rsaKey RSA密钥对
     * @return 解析成功之后的用户信息
     * @throws ParseException
     * @throws JOSEException
     * @throws JwtInvalidException
     */
    public static UserEntity verifyTokenByRSA(String token,RSAKey rsaKey) throws ParseException, JOSEException, JwtInvalidException {
        //解析token
        JWSObject jwsObject = JWSObject.parse(token);

        //获取公钥
        RSAKey verifyKey = rsaKey.toPublicJWK();

        //创建一个验签器
        RSASSAVerifier rsassaVerifier = new RSASSAVerifier(verifyKey);
        if(!jwsObject.verify(rsassaVerifier)){//验证
            throw new JwtInvalidException("Jwt无效");
        }

        //通过JSON工具解析成用户信息对象并返回
        String str = jwsObject.getPayload().toString();
        UserEntity userEntity = JSON.parseObject(str, UserEntity.class);
        return userEntity;
    }
}
