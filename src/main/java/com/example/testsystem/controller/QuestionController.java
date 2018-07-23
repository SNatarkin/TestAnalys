package com.example.testsystem.controller;

import com.example.testsystem.model.Question;
import com.example.testsystem.model.Variant;
import com.example.testsystem.repository.PartRepository;
import com.example.testsystem.repository.QuestionRepository;
import com.example.testsystem.repository.VariantRepository;
import com.example.testsystem.transfer.QuestionWithVariants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QuestionController {
    private final PartRepository partRepository;
    private final QuestionRepository questionRepository;
    private final VariantRepository variantRepository;

    @Autowired
    public QuestionController(PartRepository partRepository, QuestionRepository questionRepository, VariantRepository variantRepository) {
        this.partRepository = partRepository;
        this.questionRepository = questionRepository;
        this.variantRepository = variantRepository;
    }

    @GetMapping("/parts/{partId}/questions")
    public Page<Question> getQuestionsByPartId(@PathVariable(value = "partId") Integer partId, Pageable pageable) {
        return questionRepository.findByPartId(partId, pageable);
    }

    @GetMapping("/parts/{partId}/questionsWithVariants")
    public List<QuestionWithVariants> getQuestionsWithVariantsByPartId(@PathVariable(value = "partId") Integer partId) {
        List<Question> questions = questionRepository.findByPartId(partId);
        List<Variant> variants = variantRepository.findAllByPartId(partId);

        Map<Integer, List<Variant>> map = new HashMap<>();
        variants.forEach(variant -> {
            if (map.containsKey(variant.getQuestion().getId())) {
                map.get(variant.getQuestion().getId()).add(variant);
            } else {
                List<Variant> list = new ArrayList<>();
                list.add(variant);
                map.put(variant.getQuestion().getId(), list);
            }
        });

        List<QuestionWithVariants> result = new ArrayList<>();
        questions.forEach(question -> {
            List<Variant> list = map.get(question.getId());
            result.add(new QuestionWithVariants(question, list));
        });

        return result;
    }

    @PostMapping("/parts/{partId}/questions")
    public Question createQuestion(@PathVariable(value = "partId") Integer partId, @Valid @RequestBody Question question) {
        return partRepository.findById(partId).map(part -> {
            question.setPart(part);
            return questionRepository.save(question);
        }).orElseThrow(() -> new EntityNotFoundException(String.format("Part with id = %s not found", partId)));
    }

    @PutMapping("/parts/{partId}/questions/{questionId}")
    public Question updateQuestion(@PathVariable(value = "partId") Integer partId,
                                   @PathVariable(value = "questionId") Integer questionId,
                                   @Valid @RequestBody Question questionRequest) {
        if (!partRepository.existsById(partId)) {
            throw new EntityNotFoundException(String.format("Part with id = %s not found", partId));
        }

        return questionRepository.findById(questionId).map(question -> {
            question.setText(questionRequest.getText());
            return questionRepository.save(question);
        }).orElseThrow(() -> new EntityNotFoundException(String.format("Question with id = %s not found", questionId)));
    }

    @DeleteMapping("/part/{partId}/question/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable(value = "partId") Integer partId,
                                            @PathVariable(value = "questionId") Integer questionId) {
        if (!partRepository.existsById(partId)) {
            throw new EntityNotFoundException(String.format("Part with id = %s not found", partId));
        }

        return questionRepository.findById(questionId).map(question -> {
            questionRepository.delete(question);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new EntityNotFoundException(String.format("Question with id = %s not found", questionId)));
    }
}
