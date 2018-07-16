package com.example.testsystem.transferObjects;

public class QuestionWithPartIdOnly {
    private String text;
    private int partId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public QuestionWithPartIdOnly(String text, int partId) {
        this.text = text;
        this.partId = partId;
    }

    public QuestionWithPartIdOnly() {
    }
}
