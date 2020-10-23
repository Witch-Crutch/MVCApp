package ru.itis.witchCrutch.services.interfaces;

import ru.itis.witchCrutch.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();
    boolean userIsExist(String email);
    void addUser(User user);
    User getUserByEmailPassword(String email, String password);
    User getUserByEmail(String email);
    User getUserById(int id);
    boolean userAuth(String email, String hash);
}
