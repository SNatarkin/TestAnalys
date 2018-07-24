package com.example.testsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreatedDate
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ElementCollection
    private List<Integer> variantIds;

    public Answer(LocalDateTime dateTime, User user, List<Integer> variantIds) {
        this.dateTime = dateTime;
        this.user = user;
        this.variantIds = variantIds;
    }

    public Answer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getVariantIds() {
        return variantIds;
    }

    public void setVariantIds(List<Integer> variantIds) {
        this.variantIds = variantIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) &&
                Objects.equals(dateTime, answer.dateTime) &&
                Objects.equals(user, answer.user) &&
                Objects.equals(variantIds, answer.variantIds);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dateTime, user, variantIds);
    }
}
