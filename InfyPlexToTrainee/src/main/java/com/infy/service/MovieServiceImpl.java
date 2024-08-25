package com.infy.service;

import com.infy.dto.MovieDTO;
import com.infy.exception.InfyMovieException;
import com.infy.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "movieServiceImpl")
@Transactional
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<MovieDTO> getMovieByRating(Double fromRating) throws InfyMovieException {
        List<MovieDTO> movieDTOs = movieRepository.getMovieByRating(fromRating);
        if (movieDTOs.isEmpty()) {
            throw new InfyMovieException("Service.MOVIE_NOT_FOUND_FOR_RATING");
        }
        return movieDTOs;
    }

    @Override
    public List<MovieDTO> getHighestRatedMovie(String directorName) throws InfyMovieException {
        List<MovieDTO> movieDTOs = movieRepository.getHighestRatedMovie(directorName);
        if (movieDTOs.isEmpty()) {
            throw new InfyMovieException("Service.NO_DIRECTOR_FOUND");
        }
        return movieDTOs;
    }

    @Override
    public Float getAverageDirectorRating(String directorName) throws InfyMovieException {
        Float avgRating = movieRepository.getAverageDirectorRating(directorName);
        if (avgRating == null) {
            throw new InfyMovieException("Service.NO_DIRECTOR_FOUND");
        }
        return avgRating;
    }

    @Override
    public Long getNumberOfMoviesReleased(Integer fromYear, Integer toYear) throws InfyMovieException {
        Long countOfMovies = movieRepository.getNumberOfMoviesReleased(fromYear, toYear);
        if (countOfMovies == 0) {
            throw new InfyMovieException("Service.NO_MOVIE_RELEASED_IN_YEAR");
        }
        return countOfMovies;
    }
}
