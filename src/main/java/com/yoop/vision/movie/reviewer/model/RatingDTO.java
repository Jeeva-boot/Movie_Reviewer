package com.yoop.vision.movie.reviewer.model;

public class RatingDTO {
    private Long id;
    private int criticRating;
    private String submitterName;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getCriticRating() { return criticRating; }
    public void setCriticRating(int criticRating) { this.criticRating = criticRating; }

    public String getSubmitterName() { return submitterName; }
    public void setSubmitterName(String submitterName) { this.submitterName = submitterName; }
}

