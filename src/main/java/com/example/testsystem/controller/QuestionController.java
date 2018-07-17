package com.example.testsystem.controller;

import com.example.testsystem.model.Question;
import com.example.testsystem.repository.PartRepository;
import com.example.testsystem.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
public class QuestionController {
    private final PartRepository partRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionController(PartRepository partRepository, QuestionRepository questionRepository) {
        this.partRepository = partRepository;
        this.questionRepository = questionRepository;
    }

    @GetMapping("/parts/{partId}/questions")
    public Page<Question> getQuestionsByPartId(@PathVariable(value = "partId") Integer partId, Pageable pageable) {
        return questionRepository.findByPartId(partId, pageable);
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
            if (questionRequest.getVariants() != null && questionRequest.getVariants().size() > 0) {
                throw new UnsupportedOperationException("This method must not save or edit variants. Use methods to edit or save variant");
            }
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
