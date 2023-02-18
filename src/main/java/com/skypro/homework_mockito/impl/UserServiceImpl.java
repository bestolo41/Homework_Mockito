package com.skypro.homework_mockito.impl;

import com.skypro.homework_mockito.exception.UserNonUniqueException;
import com.skypro.homework_mockito.model.User;
import com.skypro.homework_mockito.service.UserRepository;
import com.skypro.homework_mockito.service.UserService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> getAllLogins() {
        return userRepository.getAllUsers().stream()
                .map(User::getLogin)
                .collect(Collectors.toList());
    }

    @Override
    public void addNewUser(String login, String password) {
        if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
            throw new IllegalArgumentException();
        } else if (userRepository.getUsers().containsKey(login)) {
            throw new UserNonUniqueException();
        }

        userRepository.addUser(new User(login, password));
    }
}
