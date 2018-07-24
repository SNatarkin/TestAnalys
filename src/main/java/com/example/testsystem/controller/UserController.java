package com.example.testsystem.controller;

import com.example.testsystem.model.User;
import com.example.testsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String getUsers(ModelMap modelMap) {
        List<User> users = userRepository.findAll();
        modelMap.addAttribute("usersFromServer", users);
        return "/users";
    }
}
