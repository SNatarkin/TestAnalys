package com.example.testsystem.model;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private State state;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public Role getRole() {
        return role;
    }

    public State getState() {
        return state;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private User user;

        public UserBuilder() {
            user = new User();
        }

        public UserBuilder firstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public UserBuilder login(String login) {
            user.login = login;
            return this;
        }

        public UserBuilder hashPassword(String hashPassword) {
            user.hashPassword = hashPassword;
            return this;
        }

        public UserBuilder role(Role role) {
            user.role = role;
            return this;
        }

        public UserBuilder state(State state) {
            user.state = state;
            return this;
        }

        public User build() {
            return user;
        }
    }


}
