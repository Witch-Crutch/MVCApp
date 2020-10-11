package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.User;

public interface UsersRepository extends CrudRepository<User> {
    User findByNamePassword(String name, String password);
    User findByEmail(String email);
}

