package com.infy;

import com.infy.dto.PlayerDTO;
import com.infy.service.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class ICricketToTraineeApplication implements CommandLineRunner {
    private static final Logger LOGGER = LogManager.getLogger(ICricketToTraineeApplication.class);

    @Autowired
    PlayerService playerService;

    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(ICricketToTraineeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        getFirstSevenPlayers();
        getAllPlayersByDebutDateAfter();
        getAllPlayersSortedByRanking();
        getAllPlayersByCountrySortedByRanking();
    }

    public void getFirstSevenPlayers() {
        try {
            List<PlayerDTO> playerDTOs = playerService.getFirstSevenPlayers(0, 7);
            LOGGER.info("\n");
            playerDTOs.forEach(LOGGER::info);
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(), "Some exception occurred. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    public void getAllPlayersByDebutDateAfter() {
        try {
            String debutDate = "2010";
            List<PlayerDTO> playerDTOs = playerService.getAllPlayersByDebutDateAfter(debutDate, 0, 2);
            LOGGER.info("\n");
            playerDTOs.forEach(LOGGER::info);
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(), "Some exception occurred. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    public void getAllPlayersSortedByRanking() {
        try {
            Sort sort = Sort.by("ranking");
            List<PlayerDTO> playerDTOs = playerService.getAllPlayersSortedByRanking(sort);
            LOGGER.info("\n");
            playerDTOs.forEach(LOGGER::info);
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(), "Some exception occurred. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

    private void getAllPlayersByCountrySortedByRanking() {
        try {
            Sort sort = Sort.by("ranking");
            String country = "Australia";
            List<PlayerDTO> playerDTOs = playerService.getAllPlayersOfCountrySortedByRanking(country, sort);
            LOGGER.info("\n");
            playerDTOs.forEach(LOGGER::info);
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(), "Some exception occurred. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }
}
