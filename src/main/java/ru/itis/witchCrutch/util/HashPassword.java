package ru.itis.witchCrutch.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {

    public static String getHash(String... data) {
        String hash;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(Constants.ENCODE);

            for (String salt : data) {
                messageDigest.update(salt.getBytes(Constants.CHARSET));
            }

            hash = StringReplacer.replace(new String(messageDigest.digest(), Constants.CHARSET));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException();
        }

        return hash;
    }
}
