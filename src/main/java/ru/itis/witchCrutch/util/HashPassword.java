package ru.itis.witchCrutch.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {

    public static String getHash(String... data) {
        StringBuffer hash;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(Constants.ENCODE);

            for (String salt : data) {
                messageDigest.update(salt.getBytes(Constants.CHARSET));
            }
            byte[] rawHash = messageDigest.digest();

            hash = new StringBuffer();
            for (byte b : rawHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hash.append('0');
                hash.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException();
        }

        return hash.toString();
    }
}
