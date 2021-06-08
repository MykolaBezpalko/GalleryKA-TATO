package com.gallery.webjava.db;



import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encoding passwords in MD-5
 */
public class Encoder {
    private static final Logger log = Logger.getLogger(DBManager.class);
    /**
     * Encode password into MD-5
     * @param s password for encoding
     * @return encoded password
     */
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
            log.error("cant encode password. " + e);
            return null;
        }
        return result.toString().toUpperCase();
    }

    public static boolean match(String password, String md5pass){
        String codedPass = encode(password);
        return codedPass.equals(md5pass);
    }
}
