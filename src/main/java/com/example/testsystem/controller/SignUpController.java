package com.example.testsystem.controller;

import com.example.testsystem.form.UserForm;
import com.example.testsystem.service.SingUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    private final SingUpService singUpService;

    @Autowired
    public SignUpController(SingUpService singUpService) {
        this.singUpService = singUpService;
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(UserForm userForm) {
        singUpService.signUp(userForm);
        return "redirect:/login";
    }
}
