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
    public boolean userIsExist(String email) {
        return usersRepository.findByEmail(email) != null;
    }

    @Override
    public void addUser(User user) {
        usersRepository.save(user);
    }

    @Override
    public User getUserByNamePassword(String name, String password) {
        return usersRepository.findByNamePassword(name, password);
    }

    @Override
    public User getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }


}
