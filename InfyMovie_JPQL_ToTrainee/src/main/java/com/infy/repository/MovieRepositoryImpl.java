package com.infy.repository;

import com.infy.dto.MovieDTO;
import com.infy.entity.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository(value = "movieRepository")
public class MovieRepositoryImpl implements MovieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public MovieDTO getMovieByName(String movieName) {
        List<MovieDTO> movieDTOS = null;
        String queryString = "SELECT m FROM Movie m WHERE m.movieName = :movieName";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("movieName", movieName);
        List<Movie> movies = query.getResultList();
        if (movies.isEmpty()) {
            return null;
        } else {
            movieDTOS = new ArrayList<>();
            for (Movie movie : movies) {
                MovieDTO movieDTO = new MovieDTO();
                movieDTO.setMovieId(movie.getMovieId());
                movieDTO.setMovieName(movie.getMovieName());
                movieDTO.setDirectorName(movie.getDirectorName());
                movieDTO.setImdbRating(movie.getImdbRating());
                movieDTO.setYear(movieDTO.getYear());
                return movieDTO;
            }
        }
        return null;
    }

    @Override
    public List<MovieDTO> getMoviesByImdbRating(Double fromRating, Double toRating) {
        List<MovieDTO> movieDTOS = null;
        String queryString = "SELECT m FROM Movie m WHERE m.imdbRating BETWEEN :fromRating AND :toRating";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("fromRating", fromRating);
        query.setParameter("toRating", toRating);
        List<Movie> movies = query.getResultList();
        movieDTOS = new ArrayList<>();
        for (Movie movie : movies) {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setMovieId(movie.getMovieId());
            movieDTO.setMovieName(movie.getMovieName());
            movieDTO.setDirectorName(movie.getDirectorName());
            movieDTO.setImdbRating(movie.getImdbRating());
            movieDTO.setYear(movieDTO.getYear());
            movieDTOS.add(movieDTO);
        }
        return movieDTOS;
    }

    @Override
    public List<Object[]> getMoviesNameAndYear(String directorName) {
        String queryString = "SELECT m.movieName,m.year FROM Movie m WHERE m.directorName = :directorName";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("directorName", directorName);
        return query.getResultList();
    }
}
