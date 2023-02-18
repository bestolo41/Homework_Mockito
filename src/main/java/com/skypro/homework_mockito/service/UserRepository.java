package com.skypro.homework_mockito.service;

import com.skypro.homework_mockito.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository {
    Map<String, User> getUsers();

    List<User> getAllUsers();

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByLoginAndPassword(String login, String password);

    void addUser(User user);
}
