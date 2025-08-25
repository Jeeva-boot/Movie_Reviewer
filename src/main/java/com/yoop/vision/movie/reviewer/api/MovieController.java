package com.yoop.vision.movie.reviewer.api;

import com.yoop.vision.movie.reviewer.model.MovieDTO;
import com.yoop.vision.movie.reviewer.model.MovieRequest;
import com.yoop.vision.movie.reviewer.model.RatingRequest;
import com.yoop.vision.movie.reviewer.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.createMovie(request));
    }

    @GetMapping(value = "/genre/{genre}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieDTO>> searchMoviesByGenre(@PathVariable String genre) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.searchMoviesByGenre(genre));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMovies());
    }

    @PostMapping("/{movieId}/ratings")
    public ResponseEntity<MovieDTO> addRating(@PathVariable Long movieId, @RequestBody RatingRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.addRating(movieId, request));
    }
}
