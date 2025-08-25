package com.yoop.vision.movie.reviewer.api;


import com.yoop.vision.movie.reviewer.model.RatingDTO;
import com.yoop.vision.movie.reviewer.model.RatingRequest;
import com.yoop.vision.movie.reviewer.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.createRating(request));
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RatingDTO>> getRatings() {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatings());
    }
}

