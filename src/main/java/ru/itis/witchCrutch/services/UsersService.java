package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();
    boolean userIsExist(String name, String password);
    void addUser(User user);
    User getUserByNamePassword(String name, String password);
}
