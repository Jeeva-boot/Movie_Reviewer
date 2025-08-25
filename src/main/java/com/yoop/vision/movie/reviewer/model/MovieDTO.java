package com.yoop.vision.movie.reviewer.model;

import java.util.List;

public class MovieDTO {
    private Long id;
    private String name;
    private int averageCriticRating;
    private String genre;
    private List<RatingDTO> ratings;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAverageCriticRating() { return averageCriticRating; }
    public void setAverageCriticRating(int averageCriticRating) { this.averageCriticRating = averageCriticRating; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public List<RatingDTO> getRatings() { return ratings; }
    public void setRatings(List<RatingDTO> ratings) { this.ratings = ratings; }
}

