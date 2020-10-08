package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.repositories.UsersRepository;

import java.util.List;

public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public boolean userIsExist(String name, String password) {
        return usersRepository.findByNamePassword(name, password) != null;
    }

    @Override
    public void addUser(User user) {
        usersRepository.save(user);
    }
}
