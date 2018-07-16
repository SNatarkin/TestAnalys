package com.example.testsystem.model;

import javax.validation.constraints.Email;

public class User {
    private UserType userType;
    @Email
    private String email;
}
