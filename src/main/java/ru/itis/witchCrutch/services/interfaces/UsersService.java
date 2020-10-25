package ru.itis.witchCrutch.services.interfaces;

import ru.itis.witchCrutch.models.User;

import java.util.List;

public interface UsersService {
    void addUser(User user);
    void updateUser(User user);
    boolean userIsExist(String email);
    boolean userAuth(String email, String hash);
    User getUserByEmailPassword(String email, String password);
    User getUserByEmail(String email);
    User getUserById(int id);
    List<User> getAllUsers();
}
