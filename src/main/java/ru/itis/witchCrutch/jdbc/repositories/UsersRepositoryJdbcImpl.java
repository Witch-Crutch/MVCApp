package ru.itis.witchCrutch.jdbc.repositories;

import ru.itis.witchCrutch.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final Connection connection;

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
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
}
