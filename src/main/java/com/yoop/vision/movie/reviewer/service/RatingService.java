package com.yoop.vision.movie.reviewer.service;

import com.yoop.vision.movie.reviewer.entity.Rating;
import com.yoop.vision.movie.reviewer.model.RatingDTO;
import com.yoop.vision.movie.reviewer.model.RatingRequest;
import com.yoop.vision.movie.reviewer.repo.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public RatingDTO createRating(RatingRequest request) {
        Rating rating = new Rating();
        rating.setCriticRating(request.getCriticRating());
        rating.setSubmitterName(request.getSubmitterName());

        rating = ratingRepository.save(rating);
        return toDTO(rating);
    }

    public List<RatingDTO> getRatings() {
        return ratingRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private RatingDTO toDTO(Rating rating) {
        RatingDTO dto = new RatingDTO();
        dto.setId(rating.getId());
        dto.setCriticRating(rating.getCriticRating());
        dto.setSubmitterName(rating.getSubmitterName());
        return dto;
    }
}

