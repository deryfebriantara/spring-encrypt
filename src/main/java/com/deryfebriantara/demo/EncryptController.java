package com.deryfebriantara.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

@RestController
public class EncryptController {

    public static String keySalt(String key) throws Exception{

            int keyLength = 16;

            while (keyLength < key.length()) {
                keyLength += 4;
            }

            while (key.length() < keyLength) {
                key += " ";
            }

            return key;

    }

    @RequestMapping(
            value = "/api/encrypt/{data}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String encrypt(@PathVariable String data) throws Exception {
        String salt = "1234567890";

        RSAUtil rSAUtil = new RSAUtil();
      String path =    new File(".").getCanonicalPath();
      System.out.println(path);

        String pubKey = path+"/src/main/java/com/deryfebriantara/demo/publicKey.key";
        PublicKey publicKey = rSAUtil.getPublic(pubKey);
        byte[] rsaEnc = rSAUtil.rsaEncrypt(salt.getBytes("UTF-8"), publicKey);
        String rsaEncLength = rsaEnc.length+"";
        while (rsaEncLength.length() < 4) {
            rsaEncLength = "0" + rsaEncLength;
        }

        try {
            return  "{\"AES\":" +
                    " \""+AESTools.encrypt(data, keySalt(salt))+"\" ," +
                    "\"rsaEncLength\": \""+ rsaEncLength+"\" ," +
                    "\"RSA\" : \""+rsaEnc.toString()+"\"} ";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"AES\": \" null \" ,\"RSA\": \"null\"} ";
    }


}
