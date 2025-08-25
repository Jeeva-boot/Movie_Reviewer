package com.yoop.vision.movie.reviewer.model;

public class RatingRequest {
    private int criticRating;
    private String submitterName;

    public int getCriticRating() { return criticRating; }
    public void setCriticRating(int criticRating) { this.criticRating = criticRating; }

    public String getSubmitterName() { return submitterName; }
    public void setSubmitterName(String submitterName) { this.submitterName = submitterName; }
}
