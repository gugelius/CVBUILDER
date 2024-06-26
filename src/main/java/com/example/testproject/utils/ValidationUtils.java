package com.example.testproject.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ValidationUtils {
    private  static final int PAS_LEN = 4;
    public static boolean validation(String password){
        return password.length()>=PAS_LEN;
    }
    public static String md5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}
