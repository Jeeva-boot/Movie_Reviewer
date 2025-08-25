package com.yoop.vision.movie.reviewer.entity;

import jakarta.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int criticRating;
    private String submitterName;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getCriticRating() { return criticRating; }
    public void setCriticRating(int criticRating) { this.criticRating = criticRating; }

    public String getSubmitterName() { return submitterName; }
    public void setSubmitterName(String submitterName) { this.submitterName = submitterName; }

    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
}

