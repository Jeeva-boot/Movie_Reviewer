package com.yoop.vision.movie.reviewer.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int averageCriticRating;
    private String genre;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAverageCriticRating() { return averageCriticRating; }
    public void setAverageCriticRating(int averageCriticRating) { this.averageCriticRating = averageCriticRating; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public List<Rating> getRatings() { return ratings; }
    public void setRatings(List<Rating> ratings) { this.ratings = ratings; }

    public void updateAverageRating() {
        if (!ratings.isEmpty())
            this.averageCriticRating  = (int) ratings.stream().mapToInt(Rating::getCriticRating).average().getAsDouble();
    }
}
