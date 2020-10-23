package ru.itis.witchCrutch.repositories.interfaces;

import ru.itis.witchCrutch.models.User;

import java.util.List;

public interface UsersRepository {
    void save(User user);
    boolean authUser(String email, String hash);
    User findByEmailPassword(String email, String password);
    User findByEmail(String email);
    User findById(int id);
    List<User> findAll();
}

