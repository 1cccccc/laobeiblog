package com.xi.common;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RsaUtils {
    /**
     *
     * @param rsaKey RSA密钥对
     * @return 公钥(Base64编码)
     * @throws JOSEException
     */
    public static String getPublicKey(RSAKey rsaKey) throws JOSEException {
        PublicKey publicKey = rsaKey.toPublicKey();
        byte[] bytes = publicKey.getEncoded();
        return byteToBase64(bytes);
    }

    /**
     *
     * @param rsaKey RSA密钥对
     * @return 私钥(Base64编码)
     * @throws JOSEException
     */
    public static String getPrivateKey(RSAKey rsaKey) throws JOSEException {
        PrivateKey privateKey = rsaKey.toPrivateKey();
        byte[] bytes = privateKey.getEncoded();
        return byteToBase64(bytes);
    }

    /**
     * 使用公钥对数据进行加密
     * @param content 明文
     * @param rsaKey RSA密钥对
     * @return 密文(Base64编码)
     * @throws Exception
     */
    public static String publicEncrypt(String content, RSAKey rsaKey) throws Exception{
        PublicKey publicKey = rsaKey.toPublicKey();

        Base64.Encoder encoder = Base64.getEncoder();

        //使用公钥加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        byte[] bytes = cipher.doFinal(content.getBytes());

        //返回加密后的数据
        return byteToBase64(bytes);
    }

    //私钥解密,返回base64编码的数据

    /**
     * 使用私钥对数据进行解密
     * @param content 密文
     * @param rsaKey RSA密钥
     * @return 明文(Base64编码)
     * @throws Exception
     */
    public static String privateDecrypt(String content, RSAKey rsaKey) throws Exception{
        PrivateKey privateKey = rsaKey.toPrivateKey();

        //对加密数据进行base64解码
        byte[] b = base64ToByte(content);

        //使用私钥解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] bytes = cipher.doFinal(b);

        //构建成String
        String s = new String(bytes);

        return s;
    }


    /**
     * 字节数组编码成Base64编码
     * @param bytes 字节数组
     * @return 编码后的数据
     */
    public static String byteToBase64(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Base64解码成字节数组
     * @param base64Key
     * @return 解码后的数据
     * @throws IOException
     */
    public static byte[] base64ToByte(String base64Key) throws IOException {
        return Base64.getDecoder().decode(base64Key);
    }
}
