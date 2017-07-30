package com.deryfebriantara.demo;

public class Response {

    private String aes;
    private String rsaLengthEncrypt;
    private String rsaEnc;

    public Response() {

    }

    public Response(String aes, String rsaLengthEncrypt, String rsaEnc) {
        this.aes = aes;
        this.rsaLengthEncrypt = rsaLengthEncrypt;
        this.rsaEnc = rsaEnc;
    }

    public String getAes() {
        return aes;
    }

    public String getRsaLengthEncrypt() {
        return rsaLengthEncrypt;
    }

    public String getRsaEnc() {
        return rsaEnc;
    }

    public void setAes(String aes) {
        this.aes = aes;
    }

    public void setRsaLengthEncrypt(String rsaLengthEncrypt) {
        this.rsaLengthEncrypt = rsaLengthEncrypt;
    }

    public void setRsaEnc(String rsaEnc) {
        this.rsaEnc = rsaEnc;
    }
}
