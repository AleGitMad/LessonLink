package com.example.lessonlink.model.decorator;

class Result {
    private Educator educator;
    private int addFare;
    private String decoration;

    public Educator getEducator() {
        return educator;
    }
    public int getAddFare() {
        return addFare;
    }
    public String getDecoration() {
        return decoration;
    }

    public void setEducator(Educator educator) {
        this.educator = educator;
    }

    public void setAddFare(int addFare) {
        this.addFare = addFare;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }
}