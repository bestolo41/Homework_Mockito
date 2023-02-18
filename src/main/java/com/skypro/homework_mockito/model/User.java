package com.skypro.homework_mockito.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    public final String login;
    public String password;
}
