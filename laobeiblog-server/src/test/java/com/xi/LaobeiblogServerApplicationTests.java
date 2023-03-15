package com.xi;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.xi.security.JwtUtils;
import com.xi.exception.JwtInvalidException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Base64;

@SpringBootTest
class LaobeiblogServerApplicationTests {
    private final RSAKey rsaKey=JwtUtils.loadJKSByClassPath();

    @Test
    void contextLoads() throws JOSEException, JwtInvalidException, ParseException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String str="UyFVM+g5VIC54OItvkT5nmuLJbVpWtmnSb5R9RlRoFN7j2C0NWk7lsh1XbJlOlpgj/w99Ja3lJbaZE141owjz8xXB844Vli2o75qkCOfX/AZ31rB6Tmpi9QlXj3xldmQ+gsN46PFnTHKi00AhyWJBveZ7kaV2a6ulOZywvPBsxzuwMS5pIW2BSdy0zQjk3wLzT90ek5khOHvhfME3uldJDdVgKarMj1hZ5tTCiMCBHuSiqYjAdvGyQQ95ApD+YiIbKD2fOFvVXaBy79ldLwxuGmD4l8iw1vRRa3hwn4xgGwwE5wh57wtrdfZRDedgRGN8Npv1F54C8lnyNf1PUr7l8Zz7Fexozdz1A+1a4rvvRiecpfxbCbVnRd5ooXp9GzFpe++GOT1fPi4xrhMll85WptxeqP8W5U5S9ErDPTWSW44/ryxRusw6KRPvcpHRa+GDOt8K/MlUf3tmTgmtXrC2vnWxz2hlQiE0u0LOIHFHS3zVcVK8Zm81HfNa8aol7aTt7zqcweITz7CdjlItpBwP1P7y5rMp0oMKyBaobbZeiW4NHrTdANaWZIv1jqWAmGSshIbCatw2SdMqcmlYiHVPz2GUo99NRHSQFzEsmLzL7gVOBqVJ15Xna0WKJXtz2rFVAcHBwAdS+6TJnV1dCq+tFMieeKZRlBybwnUwtWOwfQ=";
//        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();
//
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE,rsaKey.toPublicKey());
//        byte[] bytes = cipher.doFinal("000000".getBytes());
//        String s = encoder.encodeToString(str);
//        System.out.println(s);

//        byte[] bytes2 = decoder.decode();
//        System.out.println(bytes2.length);

        Cipher cipher2 = Cipher.getInstance("RSA");
        cipher2.init(Cipher.DECRYPT_MODE,rsaKey.toPrivateKey());
        byte[] bytes1 = cipher2.doFinal(str.getBytes());
        System.out.println(new String(bytes1));
    }
}
