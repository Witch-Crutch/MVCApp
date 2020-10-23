package ru.itis.witchCrutch.repositories.interfaces;

import ru.itis.witchCrutch.models.User;

public interface UsersRepository extends CrudRepository<User> {
    User findByEmailPassword(String email, String password);
    User findByEmail(String email);
    boolean authUser(String email, String hash);
    User findById(int id);
}

