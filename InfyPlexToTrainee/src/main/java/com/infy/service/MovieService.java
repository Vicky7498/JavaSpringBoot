package com.infy.service;

import com.infy.dto.MovieDTO;
import com.infy.exception.InfyMovieException;

import java.util.List;

public interface MovieService {
    public List<MovieDTO> getMovieByRating(Double fromRating) throws InfyMovieException;

    public List<MovieDTO> getHighestRatedMovie(String directorName) throws InfyMovieException;

    public Float getAverageDirectorRating(String directorName) throws InfyMovieException;

    public Long getNumberOfMoviesReleased(Integer fromYear, Integer toYear) throws InfyMovieException;
}
