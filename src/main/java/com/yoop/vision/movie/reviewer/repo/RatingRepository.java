package com.yoop.vision.movie.reviewer.repo;

import com.yoop.vision.movie.reviewer.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
