package ru.itis.witchCrutch.models;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class User {

    private String name;
    private String lastname;
    private String email;
    private String password;
    @Builder.Default
    private String profileImg = ".";
    @Builder.Default
    private Right rights = Right.USER;

    public enum Right {
        ADMIN ("ADMIN"),
        USER ("USER"),
        UNKNOWN ("UNKNOWN");

        private final String string;

        Right(String rights) {
            this.string = rights;
        }

        public String getString() {
            return string;
        }
    }
}
