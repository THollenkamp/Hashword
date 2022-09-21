package me.tucker.hashword;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(md5.toCharArray(), salt.getBytes(), 65536, 256);
            return Base64.getEncoder().encodeToString(factory.generateSecret(spec).getEncoded()).substring(0, 17);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public static String concat(String key, String domain, String user, String properties) {
        StringBuilder builder = new StringBuilder();
        builder.append(user, 0, user.length() > 4 ? 4 : user.length() - 1)
                .append(properties, properties.length() < 2 ? 0 : 1, properties.length() < 12 ? properties.length() - 1 : 12)
                .append(domain, 0, domain.length() > 2 ? 2 : domain.length() -1)
                .append(properties, properties.length() > 14 ? 14 : 0, properties.length() > 28 ? 28 : properties.length() - 1)
                .append(key.charAt(0))
                .reverse()
                .append(properties, properties.length() / 2, properties.length() - 1)
                .reverse()
                .append(key, key.length() > 5 ? 3 : 0, key.length() > 6 ? 6 : key.length() - 1)
                .append(properties.substring(properties.length() > 5 ? properties.length() - 6 : 0))
                .append(key.length())
                .reverse()
                .append(new StringBuilder(domain.substring(0, domain.length() / 2)).reverse().append(domain.substring(domain.length() / 2)))
                .append(properties)
                .append(user, user.length() > 5 ? user.length() - 6 : 0, user.length() - 3)
                .reverse()
                .append(properties)
                .reverse();
        return builder.toString();
    }

}
