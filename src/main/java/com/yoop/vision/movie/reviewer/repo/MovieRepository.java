package com.yoop.vision.movie.reviewer.repo;

import com.yoop.vision.movie.reviewer.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByName(String name);
    List<Movie> findByGenre(String genre);
}
