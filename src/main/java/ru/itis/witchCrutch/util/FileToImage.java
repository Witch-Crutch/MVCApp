package ru.itis.witchCrutch.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class FileToImage {

    public static String toImage(InputStream image) {
        String res = "/views/assets/user/profile.png";
        if (image != null) {
            byte[] target = null;
            try {
                target = new byte[image.available()];
                image.read(target);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            res = "data:image/png;base64," + Base64.getEncoder().encodeToString(target);
        }
        return res;
    }
}
