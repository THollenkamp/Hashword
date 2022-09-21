package me.tucker.hashword;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class HashManager {

    public static String getMD5(String str) {
        StringBuffer fin = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] arr = md.digest();
            for (byte data : arr) {
                fin.append(Integer.toHexString(0xff & data)).append(~(data & 0xff) ^ data);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return fin.toString();
    }

    public static String getPassword(String md5, String salt) {
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(md5.toCharArray(), salt.getBytes(), 65536, 256);
            return Base64.getEncoder().encodeToString(factory.generateSecret(spec).getEncoded()).substring(0, 17);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

}
