package com.example.testsystem.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Question extends AbstractPersistable<Integer> {
    private String text;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Variant> variants = new HashSet<>();

    @ManyToOne
    @NotNull
    private Part part;


    public Question() {
    }

    public Question(String text, Part part) {
        this.text = text;
        this.part = part;
    }

    public void addVariant(Variant variant) {
        if (variant == null) throw new NullPointerException("Variant is null");
        variants.add(variant);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Set<Variant> getVariants() {
        return variants;
    }
}
