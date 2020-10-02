package ru.itis.witchCrutch.models;

public class User {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String profileImg;
    private boolean rights;

    public User(String name, String surname, String email, String password, String profileImg, boolean rights) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.profileImg = profileImg;
        this.rights = rights;
    }

    public User(String name, String surname, String email, String password, String profileImg) {
        this(name, surname, email, password, profileImg, false);
    }

    public User(String name, String surname, String email, String password) {
        this(name, surname, email, password, ".", false);
    }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getProfileImg() { return profileImg; }

    public boolean isRights() { return rights; }

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
}
