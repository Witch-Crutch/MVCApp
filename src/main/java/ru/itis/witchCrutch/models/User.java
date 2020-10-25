package ru.itis.witchCrutch.models;


import lombok.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Data
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class User {

    private int id;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private InputStream profileImg;
    @Builder.Default
    private String image = "/views/assets/user/profile.png";
    @Builder.Default
    private Right rights = Right.USER;

    public String getProImage() {
        if (profileImg == null) {
            return image;
        }

        byte[] target = null;
        try {
            target = new byte[profileImg.available()];
            profileImg.read(target);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        String res = "data:image/png;base64," + Base64.getEncoder().encodeToString(target);
        this.image = res;
        return res;
    }

    public enum Right {
        ADMIN("ADMIN"),
        USER("USER"),
        UNKNOWN("UNKNOWN");

        private final String string;

        Right(String rights) {
            this.string = rights;
        }

        public String getString() {
            return string;
        }
    }
}
