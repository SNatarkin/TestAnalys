package com.example.testsystem.controller;

import com.example.testsystem.model.Part;
import com.example.testsystem.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
public class PartController {
    private final PartRepository partRepository;

    @Autowired
    public PartController(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @GetMapping("/parts")
    public Page<Part> getAllParts(Pageable pageable) {
        return partRepository.findAll(pageable);
    }

    @PostMapping("/parts")
    public Part createPart(@Valid @RequestBody Part part) {
        return partRepository.save(part);
    }

    @PutMapping("/parts/{partId}")
    public Part updatePart(@PathVariable Integer partId, @Valid @RequestBody Part partRequest) {
        return partRepository.findById(partId).map(part -> {
            part.setName(partRequest.getName());
            return partRepository.save(part);
        }).orElseThrow(() -> new EntityNotFoundException(String.format("Part with id = %s not found", partId)));
    }

    @DeleteMapping("parts/{partId}")
    public ResponseEntity<?> deletePart(@PathVariable Integer partId) {
        return partRepository.findById(partId).map(part -> {
            partRepository.delete(part);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new EntityNotFoundException(String.format("Part with id = %s not found", partId)));
    }
}
