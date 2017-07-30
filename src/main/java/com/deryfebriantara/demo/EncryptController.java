package com.deryfebriantara.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Arrays;
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
            produces = { "application/json" }
    )
    public @ResponseBody List<Response> encrypt(@PathVariable String data) throws Exception {
        String salt = "012345678901234567890";

        RSAUtil rSAUtil = new RSAUtil();
        String path =    new File(".").getCanonicalPath();
        String pubKey = path+"/src/main/java/com/deryfebriantara/demo/publicKey.key";
        PublicKey publicKey = rSAUtil.getPublic(pubKey);
        byte[] rsaEnc = rSAUtil.rsaEncrypt(salt.getBytes("UTF-8"), publicKey);
        System.out.println(new String(rsaEnc));
        String rsaEncLength = rsaEnc.length+"";
        while (rsaEncLength.length() < 4) {
            rsaEncLength = "0" + rsaEncLength;
        }


        String Aes = AESTools.encrypt(data, keySalt(salt));

        try {
           return Arrays.asList(
                   new Response(Aes, rsaEncLength, new String(rsaEnc) )
           );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
