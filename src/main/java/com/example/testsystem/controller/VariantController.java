package com.example.testsystem.controller;

import com.example.testsystem.model.Variant;
import com.example.testsystem.repository.PartRepository;
import com.example.testsystem.repository.QuestionRepository;
import com.example.testsystem.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
public class VariantController {

    private final PartRepository partRepository;
    private final QuestionRepository questionRepository;
    private final VariantRepository variantRepository;


    @Autowired
    public VariantController(PartRepository partRepository, QuestionRepository questionRepository, VariantRepository variantRepository) {
        this.partRepository = partRepository;
        this.questionRepository = questionRepository;
        this.variantRepository = variantRepository;
    }

    @GetMapping("/parts/{partId}/questions/{questionId}/variants")
    public List<Variant> getVariants(@PathVariable(name = "partId") Integer partId,
                                     @PathVariable(name = "questionId") Integer questionId) {
        if (!partRepository.existsById(partId)) {
            throw new EntityNotFoundException(String.format("Part with id = %s not found", partId));
        }
        if (!questionRepository.existsById(questionId)) {
            throw new EntityNotFoundException(String.format("Question with id = %s not found", questionId));
        }

        return variantRepository.findByQuestionId(questionId);

    }

    @PostMapping("/parts/{partId}/questions/{questionId}/variants")
    public Variant createVariant(@PathVariable(name = "partId") Integer partId,
                                 @PathVariable(name = "questionId") Integer questionId,
                                 @Valid @RequestBody Variant variant) {
        if (!partRepository.existsById(partId)) {
            throw new EntityNotFoundException(String.format("Part with id = %s not found", partId));
        }

        return questionRepository.findById(questionId).map(question -> {
            variant.setQuestion(question);
            return variantRepository.save(variant);
        }).orElseThrow(() -> new EntityNotFoundException(String.format("Question with id = %s not found", questionId)));
    }

    @PutMapping("/parts/{partId}/questions/{questionId}/variants/{variantId}")
    public Variant updateVariant(@PathVariable(name = "partId") Integer partId,
                                 @PathVariable(name = "questionId") Integer questionId,
                                 @PathVariable(name = "variantId") Integer variantId,
                                 @Valid @RequestBody Variant variantRequest) {
        if (!partRepository.existsById(partId)) {
            throw new EntityNotFoundException(String.format("Part with id = %s not found", partId));
        }
        if (!questionRepository.existsById(questionId)) {
            throw new EntityNotFoundException(String.format("Question with id = %s not found", questionId));
        }

        return variantRepository.findById(variantId).map(variant -> {
            variant.setText(variantRequest.getText());
            variant.setTrue(variantRequest.isTrue());
            return variantRepository.save(variant);
        }).orElseThrow(() -> new EntityNotFoundException(String.format("Variant with id = %s not found", variantId)));
    }

    @DeleteMapping("/parts/{partId}/questions/{questionId}/variants/{variantId}")
    public ResponseEntity<?> deleteVariant(@PathVariable(name = "partId") Integer partId,
                                           @PathVariable(name = "questionId") Integer questionId,
                                           @PathVariable(name = "variantId") Integer variantId) {

        if (!partRepository.existsById(partId)) {
            throw new EntityNotFoundException(String.format("Part with id = %s not found", partId));
        }
        if (!questionRepository.existsById(questionId)) {
            throw new EntityNotFoundException(String.format("Question with id = %s not found", questionId));
        }

        return variantRepository.findById(variantId).map(variant -> {
            variantRepository.delete(variant);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new EntityNotFoundException(String.format("Variant with id = %s not found", variantId)));
    }
}
