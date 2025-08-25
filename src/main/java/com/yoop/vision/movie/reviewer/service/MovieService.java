package com.yoop.vision.movie.reviewer.service;

import com.yoop.vision.movie.reviewer.entity.Movie;
import com.yoop.vision.movie.reviewer.entity.Rating;
import com.yoop.vision.movie.reviewer.error.exception.MovieAlreadyExistsException;
import com.yoop.vision.movie.reviewer.error.exception.MovieNotFoundException;
import com.yoop.vision.movie.reviewer.model.MovieDTO;
import com.yoop.vision.movie.reviewer.model.MovieRequest;
import com.yoop.vision.movie.reviewer.model.RatingDTO;
import com.yoop.vision.movie.reviewer.model.RatingRequest;
import com.yoop.vision.movie.reviewer.repo.MovieRepository;
import com.yoop.vision.movie.reviewer.repo.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public MovieDTO createMovie(MovieRequest request) {
        if (movieRepository.findByName(request.getName()).isPresent()) {
            LOG.error("Movie with name '{}' already exists", request.getName());
            throw new MovieAlreadyExistsException("Movie with name '" + request.getName() + "' already exists");
        }

        Movie movie = new Movie();
        movie.setName(request.getName());
        movie.setGenre(request.getGenre());
        movie.setAverageCriticRating(0);

        return toDTO(movieRepository.save(movie));
    }

    public List<MovieDTO> searchMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public MovieDTO addRating(Long movieId, RatingRequest request) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + movieId));

        Rating rating = new Rating();
        rating.setCriticRating(request.getCriticRating());
        rating.setSubmitterName(request.getSubmitterName());
        rating.setMovie(movie);

        ratingRepository.save(rating);

        movie.getRatings().add(rating);
        movie.updateAverageRating();

        return toDTO(movieRepository.save(movie));
    }

    private MovieDTO toDTO(Movie movie) {
        MovieDTO movieDto = new MovieDTO();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());
        movieDto.setGenre(movie.getGenre());
        movieDto.setAverageCriticRating(movie.getAverageCriticRating());

        movieDto.setRatings(
                movie.getRatings().stream()
                        .map(r -> {
                            RatingDTO ratingDto = new RatingDTO();
                            ratingDto.setId(r.getId());
                            ratingDto.setCriticRating(r.getCriticRating());
                            ratingDto.setSubmitterName(r.getSubmitterName());
                            return ratingDto;
                        })
                        .collect(Collectors.toList())
        );
        return movieDto;
    }
}
