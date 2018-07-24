package com.example.testsystem.controller;

import com.example.testsystem.model.Answer;
import com.example.testsystem.model.User;
import com.example.testsystem.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AnswerController {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerController(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @GetMapping("/users/{userId}/answers")
    public List<Answer> getAnswerByUserId(@PathVariable(name = "userId") Integer userId) {
        return answerRepository.getAnswersByUserId(userId);
    }

   /* @PostMapping("/users/{userId}/answers")
    public Answer createAnswer(@PathVariable(name = "userId") Integer userId,
                               @RequestBody @Valid Answer answer) {
        User user = new User(UserType.USER, "qwe@mail.ru");
        answer.setUser(user);
        return answerRepository.save(answer);
    }*/
}
