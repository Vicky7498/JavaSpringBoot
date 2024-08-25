package com.infy.service;

import com.infy.dto.MovieDTO;
import com.infy.exception.InfyMovieException;

import java.util.List;

public interface MovieService {
    public MovieDTO getMovieByName(String movieName) throws InfyMovieException;

    public List<MovieDTO> getMoviesByImdbRating(Double fromRating, Double toRating) throws InfyMovieException;

    public List<Object[]> getMoviesNameAndYear(String directorName) throws InfyMovieException;
}
