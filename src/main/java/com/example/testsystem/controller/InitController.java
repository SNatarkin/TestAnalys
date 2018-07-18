package com.example.testsystem.controller;

import com.example.testsystem.model.Part;
import com.example.testsystem.model.Question;
import com.example.testsystem.model.Variant;
import com.example.testsystem.repository.PartRepository;
import com.example.testsystem.repository.QuestionRepository;
import com.example.testsystem.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {
    private final PartRepository partRepository;
    private final QuestionRepository questionRepository;
    private final VariantRepository variantRepository;

    @Autowired
    public InitController(PartRepository partRepository, QuestionRepository questionRepository, VariantRepository variantRepository) {
        this.partRepository = partRepository;
        this.questionRepository = questionRepository;
        this.variantRepository = variantRepository;
    }

    @PostMapping("/init")
    public void init() {
        Part math = partRepository.save(new Part("math"));
        Question question1 = questionRepository.save(new Question("2+2=?", math));
        variantRepository.save(new Variant("3", false, question1));
        variantRepository.save(new Variant("4", true, question1));
        variantRepository.save(new Variant("5", false, question1));
        Question question2 = questionRepository.save(new Question("5*5=?", math));
        variantRepository.save(new Variant("10", false, question2));
        variantRepository.save(new Variant("15", false, question2));
        variantRepository.save(new Variant("25", true, question2));

    }
}
