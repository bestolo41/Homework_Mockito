package com.skypro.homework_mockito.impl;

import com.skypro.homework_mockito.exception.UserNonUniqueException;
import com.skypro.homework_mockito.model.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static com.skypro.homework_mockito.impl.UserRepositoryTestConstants.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepositoryImpl userRepository;

    @InjectMocks
    private UserServiceImpl out;

    @ParameterizedTest
    @MethodSource("provideParamsForGetALLLoginsTests")
    void getAllLogins(List<User> mockArg, List<String> expectedList) {
        when(userRepository.getAllUsers()).thenReturn(mockArg);
        assertEquals(out.getAllLogins(), expectedList);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForAddNewUserTests")
    void addNewUserWithEmptyLoginOrPassword(String login, String password) {
        assertThrows(IllegalArgumentException.class, () -> out.addNewUser(login, password));
    }

    @Test
    void addNewUserWithExistingLogin() {
        when(userRepository.getUsers()).thenReturn(USER_MAP);
        assertThrows(UserNonUniqueException.class, () -> out.addNewUser(EXISTING_LOGIN, NON_EXISTENT_PASSWORD));
    }

    @Test
    void addNewUserWithNonExistingLoginAndPassword() {
        when(userRepository.getUsers()).thenReturn(USER_MAP);
        assertDoesNotThrow(() -> out.addNewUser(NON_EXISTENT_LOGIN, NON_EXISTENT_PASSWORD));
    }

    public static Stream<Arguments> provideParamsForGetALLLoginsTests() {
        return Stream.of(
                Arguments.of(USERS_LIST, NON_NULL_LOGIN_LIST),
                Arguments.of(EMPTY_LIST, NULL_LOGIN_LIST));
    }

    public static Stream<Arguments> provideParamsForAddNewUserTests() {
        return Stream.of(
                Arguments.of(EMPTY_LOGIN, NON_EXISTENT_PASSWORD),
                Arguments.of(NON_EXISTENT_LOGIN, EMPTY_PASSWORD));
    }
}