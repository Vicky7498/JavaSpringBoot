package com.infy.repository;

import com.infy.dto.MovieDTO;

import java.util.List;

public interface MovieRepository {
    public List<MovieDTO> getMovieByRating(Double fromRating);

    public List<MovieDTO> getHighestRatedMovie(String directorName);

    public Float getAverageDirectorRating(String directorName);

    public Long getNumberOfMoviesReleased(Integer fromYear, Integer toYear);
}
