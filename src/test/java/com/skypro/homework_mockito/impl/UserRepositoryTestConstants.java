package com.skypro.homework_mockito.impl;

import com.skypro.homework_mockito.exception.UserNonUniqueException;
import com.skypro.homework_mockito.model.User;

import java.util.*;

public class UserRepositoryTestConstants {
    public static final List<User> EMPTY_LIST = new ArrayList<>();
    public static final User USER_1 = new User("igor", "1563216");
    public static final User USER_2 = new User("dima", "5315177");
    public static final User USER_3 = new User("sveta", "8655842");

    public static final Map<String, User> USER_MAP = Map.of(
            USER_1.getLogin(), USER_1,
            USER_2.getLogin(), USER_2,
            USER_3.getLogin(), USER_3
    );

    public static final List<User> USERS_LIST = Arrays.asList(USER_1, USER_2, USER_3);
    public static final String EXISTING_LOGIN = USER_1.getLogin();
    public static final String EXISTING_PASSWORD = USER_1.getPassword();
    public static final String NON_EXISTENT_LOGIN = "vlad";
    public static final String NON_EXISTENT_PASSWORD = "1565414";
    public static final String EMPTY_LOGIN = "";
    public static final String EMPTY_PASSWORD = "";
    public static final Optional<User> NON_NULL_OPT = Optional.of(USER_1);
    public static final Optional<User> NULL_OPT = Optional.empty();
    public static final List<String> NON_NULL_LOGIN_LIST = List.of(USER_1.getLogin(), USER_2.getLogin(), USER_3.getLogin());
    public static final List<String> NULL_LOGIN_LIST = new ArrayList<>();

}
