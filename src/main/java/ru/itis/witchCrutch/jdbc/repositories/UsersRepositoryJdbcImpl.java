package ru.itis.witchCrutch.jdbc.repositories;

import ru.itis.witchCrutch.jdbc.SimpleDataSource;
import ru.itis.witchCrutch.models.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final Connection connection;
    private final SimpleDataSource sds;

    public UsersRepositoryJdbcImpl() {
        SimpleDataSource simpleDataSource = new SimpleDataSource();
        this.sds = simpleDataSource;
        this.connection = simpleDataSource.createConnection();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");

            while(rs.next()) {
                users.add(new User(
                        rs.getString("name"), rs.getString("surname"),
                        rs.getString("email"), rs.getString("password"))
                );
            }

            rs.close();
        } catch (SQLException throwables) {
            throw new IllegalArgumentException();
        }

        return users;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    public void close() {
        sds.closeConnection(connection);
    }

    public boolean userIsExist(String name, String password) {
        boolean result = false;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            messageDigest.update(name.getBytes(StandardCharsets.UTF_8));
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));

            byte[] cryptPassword = messageDigest.digest();

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users where name='" + name + "' AND password='" + new String(cryptPassword) + "';";

            ResultSet resultSet = statement.executeQuery(query);
            result = resultSet.next();

        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    public void addUser(User user) {
        String query = "INSERT INTO users (name, email, surname, password, profile_img, rights)" +
                "VALUES (?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getProfileImg());
            statement.setString(6, user.getRights().getString());

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}
