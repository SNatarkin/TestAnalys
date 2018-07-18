package com.example.testsystem.transferObject;

import com.example.testsystem.model.Question;
import com.example.testsystem.model.Variant;

import java.util.List;

public class QuestionWithVariants {
    private Question question;
    private List<Variant> variants;

    public QuestionWithVariants(Question question, List<Variant> variants) {
        this.question = question;
        this.variants = variants;
    }

    public QuestionWithVariants() {
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }
}
