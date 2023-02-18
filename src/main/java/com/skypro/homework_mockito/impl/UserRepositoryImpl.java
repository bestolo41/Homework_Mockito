package com.skypro.homework_mockito.impl;

import com.skypro.homework_mockito.model.User;
import com.skypro.homework_mockito.service.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {
    Map<String, User> users = new HashMap<>();

    @Override
    public Map<String, User> getUsers() {
        return users;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return Optional.ofNullable(users.get(login));
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        return users.values().stream()
                .filter(u -> login.equals(u.getLogin()) && password.equals(u.getPassword()))
                .findFirst();
    }

    @Override
    public void addUser(User user) {
        users.put(user.getLogin(), user);
    }
}
