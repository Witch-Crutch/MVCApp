package ru.itis.witchCrutch.models;

import lombok.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Base64;

@Data
@ToString
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class Message {
    User sender;
    User receiver;
    String message;
    InputStream file;
    @Builder.Default
    Timestamp date = new Timestamp(System.currentTimeMillis());
    String image;

    public String getImage() {
        if (image != null) {
            return this.image;
        }
        String res = "";

        if (file != null) {
            byte[] target = null;
            try {
                target = new byte[file.available()];
                file.read(target);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            res = "data:image/png;base64," + Base64.getEncoder().encodeToString(target);
            this.image = res;
        }
        return res;
    }
}
