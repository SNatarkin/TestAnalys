package com.example.testsystem.service;

import com.example.testsystem.model.Part;
import com.example.testsystem.model.Question;
import com.example.testsystem.repository.PartRepository;
import com.example.testsystem.repository.QuestionRepository;
import com.example.testsystem.transferObjects.QuestionWithPartIdOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final PartRepository partRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, PartRepository partRepository) {
        this.questionRepository = questionRepository;
        this.partRepository = partRepository;
    }

    @Transactional
    public Question addQuestionWithPartIdOnly(QuestionWithPartIdOnly questionWithPartIdOnly) {
        Optional<Part> byId = partRepository.findById(questionWithPartIdOnly.getPartId());
        Part part = byId.get();
        Question question = new Question(questionWithPartIdOnly.getText(), part);
        return questionRepository.save(question);
    }
}
