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
    public List<MovieDTO> getMovieByRating(Double fromRating) {
        List<MovieDTO> movieDTOS = null;
        String queryString = "SELECT m FROM Movie m WHERE m.imdbRating >= :fromRating ORDER BY m.imdbRating ASC";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("fromRating", fromRating);
        List<Movie> movies = query.getResultList();
        movieDTOS = new ArrayList<>();
        List<MovieDTO> finalMovieDTOS = movieDTOS;
        movies.forEach(movie -> {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setMovieId(movie.getMovieId());
            movieDTO.setMovieName(movie.getMovieName());
            movieDTO.setDirectorName(movie.getDirectorName());
            movieDTO.setImdbRating(movie.getImdbRating());
            movieDTO.setYear(movie.getYear());
            finalMovieDTOS.add(movieDTO);
        });
        return finalMovieDTOS;
    }

    @Override
    public List<MovieDTO> getHighestRatedMovie(String directorName) {
        List<MovieDTO> movieDTOS = null;
        String queryString = "WITH FirstRecord AS (SELECT m as record,ROW_NUMBER() OVER (ORDER BY m.imdbRating DESC) AS row_num FROM Movie m WHERE m.directorName = :directorName) SELECT f.record FROM FirstRecord f WHERE row_num = 1";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("directorName", directorName);
        List<Movie> movies = query.getResultList();
        movieDTOS = new ArrayList<>();
        List<MovieDTO> finalMovieDTOS = movieDTOS;
        movies.forEach(movie -> {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setMovieId(movie.getMovieId());
            movieDTO.setMovieName(movie.getMovieName());
            movieDTO.setDirectorName(movie.getDirectorName());
            movieDTO.setImdbRating(movie.getImdbRating());
            movieDTO.setYear(movie.getYear());
            finalMovieDTOS.add(movieDTO);
        });
        return finalMovieDTOS;
    }

    @Override
    public Float getAverageDirectorRating(String directorName) {
        String queryString = "SELECT AVG(m.imdbRating) FROM Movie m WHERE m.directorName = :directorName";
        //String queryString = "IF EXISTS (SELECT 1 FROM Movie m WHERE m.directorName = :directorName) BEGIN SELECT AVG(m.imdbRating) FROM Movie m WHERE m.directorName = :directorName END ELSE BEGIN return null END";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("directorName", directorName);
        //return (Float) query.getSingleResult();
        return 0F;
    }

    @Override
    public Long getNumberOfMoviesReleased(Integer fromYear, Integer toYear) {
        String queryString = "SELECT COUNT(*) FROM Movie m WHERE m.year >= :fromYear AND m.year <= :toYear";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("fromYear", fromYear);
        query.setParameter("toYear", toYear);
        return (Long) query.getSingleResult();
    }
}
