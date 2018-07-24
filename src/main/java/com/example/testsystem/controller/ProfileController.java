package com.example.testsystem.controller;

import com.example.testsystem.security.details.UserDetailsImpl;
import com.example.testsystem.transfer.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @GetMapping("/")
    public String getProfilePage(ModelMap model, Authentication authentication) {
        if (authentication == null) return "redirect:/login";
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserDto user = UserDto.from(userDetails.getUser());
        model.addAttribute("user", user);
        return "profile";
    }
}
