package com.deryfebriantara.demo;


import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESTools {
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = new byte[]{'D', '0', 'K', 'u', 'a', 'C', 'c', '0', 'u', 'N', 'T', 'B', 'i', 'l', 'L', '5'};

    public static byte[] encrypt(byte[] data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(data);
        return encValue;
    }

    public static byte[] decrypt(byte[] data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decValue = c.doFinal(data);
        return decValue;
    }

    public static String encrypt(String valueToEnc) {
        String result = "";
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encValue = c.doFinal(valueToEnc.getBytes());
            result = new BASE64Encoder().encode(encValue);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return result;
    }

    public static String encrypt(String valueToEnc, String keyString) throws Exception {
        Key key = generateKey(keyString);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(valueToEnc.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encValue);
        return encryptedValue;
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }

    private static Key generateKey(String keyString) throws Exception {
        Key key = new SecretKeySpec(keyString.getBytes(), ALGORITHM);
        return key;
    }





}
