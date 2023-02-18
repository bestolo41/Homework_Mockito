package com.skypro.homework_mockito.impl;

import com.skypro.homework_mockito.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.skypro.homework_mockito.impl.UserRepositoryTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {
    private final UserRepositoryImpl out = new UserRepositoryImpl();

    @Test
    void shouldReturnEmptyMapWhenGetEmptyMap() {
        assertEquals(EMPTY_LIST, out.getAllUsers());
    }

    @Test
    void shouldReturnSameUsers() {
        USERS_LIST.forEach(out::addUser);
        assertTrue(out.getAllUsers().containsAll(USERS_LIST));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForSearchByLoginAndPasswordTests")
    void shouldReturnUserOrNullWhenSearchByLoginAndPassword(String login, String password, Optional<User> expectedOpt, String message) {
        USERS_LIST.forEach(out::addUser);
        assertEquals(out.findUserByLoginAndPassword(login, password), expectedOpt, message);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForSearchByLoginTests")
    void shouldReturnUserOrNullWhenSearchByLogin(String login, Optional<User> expectedOpt) {
        USERS_LIST.forEach(out::addUser);
        assertEquals(out.findUserByLogin(login), expectedOpt);
    }

    public static Stream<Arguments> provideParamsForSearchByLoginAndPasswordTests() {
        return Stream.of(
                Arguments.of(EXISTING_LOGIN, EXISTING_PASSWORD, NON_NULL_OPT, "Существующий логин, Существующий пароль, ожидаем не пустой Optional"),
                Arguments.of(EXISTING_LOGIN, NON_EXISTENT_PASSWORD, NULL_OPT, "Существующий логин, Несуществующий пароль, ожидаем пустой Optional"),
                Arguments.of(NON_EXISTENT_LOGIN, EXISTING_PASSWORD, NULL_OPT, "Несуществующий логин, Существующий пароль, ожидаем пустой Optional"));
    }

    public static Stream<Arguments> provideParamsForSearchByLoginTests() {
        return Stream.of(
                Arguments.of(EXISTING_LOGIN, NON_NULL_OPT),
                Arguments.of(NON_EXISTENT_LOGIN, NULL_OPT));
    }

}