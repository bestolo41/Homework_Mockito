package com.skypro.homework_mockito.service;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<String> getAllLogins();

    void addNewUser(String login, String password);

}
