package com.infy.service;

import com.infy.dto.PlayerDTO;
import com.infy.entity.Players;
import com.infy.exception.InfyPlayerException;
import com.infy.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service(value = "playerService")
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public List<PlayerDTO> getFirstSevenPlayers(int pageNo, int pageSize) throws InfyPlayerException {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Players> players = playerRepository.findAll(pageable);
        if (players.isEmpty()) {
            throw new InfyPlayerException("Service.NO_PLAYERS_IN_THIS_PAGE");
        }
        List<Players> playersList = players.getContent();
        return playersList.stream().map(player -> new PlayerDTO(player.getPlayerId(), player.getPlayerName(), player.getRanking(), player.getBattingStyle(), player.getBowlingStyle(), player.getDebutDate(), player.getCountry())).collect(Collectors.toList());
    }

    @Override
    public List<PlayerDTO> getAllPlayersByDebutDateAfter(String debutDate, int pageNo, int pageSize) throws InfyPlayerException {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Players> playersList = playerRepository.findByDebutDateAfter(debutDate, pageable);
        if (playersList.isEmpty()) {
            throw new InfyPlayerException("Service.NO_PLAYERS_IN_THIS_PAGE");
        }
        return playersList.stream().map(player -> new PlayerDTO(player.getPlayerId(), player.getPlayerName(), player.getRanking(), player.getBattingStyle(), player.getBowlingStyle(), player.getDebutDate(), player.getCountry())).collect(Collectors.toList());
    }

    @Override
    public List<PlayerDTO> getAllPlayersSortedByRanking(Sort sort) throws InfyPlayerException {
        Iterable<Players> playersIterable = playerRepository.findAll(sort);
        if (!playersIterable.iterator().hasNext()) {
            throw new InfyPlayerException("Service.NO_PLAYERS_IN_THIS_PAGE");
        }
        List<Players> playersList = StreamSupport.stream(playersIterable.spliterator(), false).toList();
        return playersList.stream().map(player -> new PlayerDTO(player.getPlayerId(), player.getPlayerName(), player.getRanking(), player.getBattingStyle(), player.getBowlingStyle(), player.getDebutDate(), player.getCountry())).collect(Collectors.toList());
    }

    @Override
    public List<PlayerDTO> getAllPlayersOfCountrySortedByRanking(String country, Sort sort) throws InfyPlayerException {
        List<Players> playersList = playerRepository.findByCountry(country, sort);
        if (playersList.isEmpty()) {
            throw new InfyPlayerException("Service.NO_PLAYERS_IN_THIS_PAGE");
        }
        return playersList.stream().map(player -> new PlayerDTO(player.getPlayerId(), player.getPlayerName(), player.getRanking(), player.getBattingStyle(), player.getBowlingStyle(), player.getDebutDate(), player.getCountry())).collect(Collectors.toList());
    }
}
