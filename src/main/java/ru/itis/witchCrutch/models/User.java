package ru.itis.witchCrutch.models;


import lombok.*;
import ru.itis.witchCrutch.util.FileToImage;

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
    private Right rights = Right.USER;
    @Builder.Default
    private String image = "/views/assets/user/profile.png";

    public String getMesImage() {
        return FileToImage.toImage(profileImg);
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
