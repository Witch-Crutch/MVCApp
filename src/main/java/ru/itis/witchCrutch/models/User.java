package ru.itis.witchCrutch.models;

public class User {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String profileImg;
    private Right rights;

    public User(String name, String surname, String email, String password, String profileImg, Right rights) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.profileImg = profileImg;
        this.rights = rights;
    }

    public User(String name, String surname, String email, String password, String profileImg) {
        this(name, surname, email, password, profileImg, Right.USER);
    }

    public User(String name, String surname, String email, String password) {
        this(name, surname, email, password, ".", Right.USER);
    }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getProfileImg() { return profileImg; }

    public Right getRights() { return rights; }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", rights=" + rights +
                '}';
    }

    public enum Right {
        ADMIN ("admin"),
        USER ("user"),
        UNKNOWN ("unknown");

        private final String string;

        Right(String rights) {
            this.string = rights;
        }

        public String getString() {
            return string;
        }
    }
}
