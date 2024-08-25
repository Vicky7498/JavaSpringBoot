package com.infy;

import com.infy.dto.MovieDTO;
import com.infy.exception.InfyMovieException;
import com.infy.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class InfyMovieJpqlToTraineeApplication implements CommandLineRunner {
    private static final Logger LOGGER = LogManager.getLogger(InfyMovieJpqlToTraineeApplication.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(InfyMovieJpqlToTraineeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        getMovieByName();
        getMovieByImdbRating();
        getMoviesNameAndYear();
    }

    private void getMovieByName() {
        try {
            String movieName = "Deadpool";
            MovieDTO movieDTO = movieService.getMovieByName(movieName);
            LOGGER.info(movieDTO);
            LOGGER.info("/n");
        } catch (Exception exception) {
            LOGGER.info(environment.getProperty(exception.getMessage(), "Some exception occured.Please check log file."));
        }
    }

    private void getMovieByImdbRating() {
        try {
            Double fromRating = 0.0d;
            Double toRating = 7.8d;
            List<MovieDTO> movieDTOS = movieService.getMoviesByImdbRating(fromRating, toRating);
            movieDTOS.forEach(movie -> LOGGER.info(movie));
            LOGGER.info("\n");
        } catch (Exception exception) {
            LOGGER.info(environment.getProperty(exception.getMessage(), "Some exception occured.Please check log file."));
            LOGGER.info("\n");
        }
    }

    private void getMoviesNameAndYear() {
        try {
            String directorName = "Joss Whedon";
            List<Object[]> objects = movieService.getMoviesNameAndYear(directorName);
            for (Object[] object : objects) {
                LOGGER.info(object[0] + "\t" + object[1]);
            }
            LOGGER.info("\n");
        } catch (InfyMovieException e) {
            LOGGER.info(environment.getProperty(e.getMessage(), "Some exception occured.Please check log file."));
            LOGGER.info("\n");
        }
    }
}
