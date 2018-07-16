package com.example.testsystem.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Variant extends AbstractPersistable<Integer> {
    private String text;
    private boolean isTrue;
    @ManyToOne
    @NotNull
    private Question question;

    public Variant(String text, boolean isTrue, Question question) {
        this.text = text;
        this.isTrue = isTrue;
        this.question = question;
    }

    public Variant() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getTrue() {
        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
    }
}
