package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.repositories.interfaces.UsersRepository;
import ru.itis.witchCrutch.services.interfaces.UsersService;

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
    public User getUserByEmailPassword(String email, String password) {
        return usersRepository.findByEmailPassword(email, password);
    }

    @Override
    public User getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public User getUserById(int id) {
        return usersRepository.findById(id);
    }

    @Override
    public boolean userAuth(String email, String hash) { return usersRepository.authUser(email, hash); }


}
