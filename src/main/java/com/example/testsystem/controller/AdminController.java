package com.example.testsystem.controller;

import com.example.testsystem.model.Part;
import com.example.testsystem.model.Question;
import com.example.testsystem.model.Variant;
import com.example.testsystem.repository.PartRepository;
import com.example.testsystem.repository.VariantRepository;
import com.example.testsystem.service.QuestionService;
import com.example.testsystem.transferObjects.QuestionWithPartIdOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    private final QuestionService questionService;
    private final PartRepository partRepository;
    private final VariantRepository variantRepository;

    @Autowired
    public AdminController(QuestionService questionService, PartRepository partRepository, VariantRepository variantRepository) {
        this.questionService = questionService;
        this.partRepository = partRepository;
        this.variantRepository = variantRepository;
    }


    @PostMapping("addPart")
    public Part addPart(@RequestBody Part part) {
        return partRepository.save(part);
    }

    @PostMapping("addQuestionWithPartIdOnly")
    public Question addQuestionWithPartIdOnly(@RequestBody QuestionWithPartIdOnly questionWithPartIdOnly) {
        Question question = questionService.addQuestionWithPartIdOnly(questionWithPartIdOnly);
        return question;
    }

    @PostMapping("addVariant")
    public Variant addVariant(@RequestBody Variant variant) {
        return variantRepository.save(variant);
    }


}
