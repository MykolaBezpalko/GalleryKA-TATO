package com.gallery.webjava.db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {
    public static String encode(String s) {
        StringBuilder result= new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes());
            byte[] messageDigest = md.digest();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                result.append(h);
            }

        } catch (NoSuchAlgorithmException e) {
            System.err.println("cant encode password");
            e.printStackTrace();
            return null;
        }
        return result.toString().toUpperCase();
    }

    public static boolean match(String password, String md5pass){
        String codedPass = encode(password);
        return codedPass.equals(md5pass);
    }
}
