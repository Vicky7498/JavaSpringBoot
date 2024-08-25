package com.infy.repository;

import com.infy.entity.Players;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PlayerRepository extends PagingAndSortingRepository<Players, Integer> {
    public List<Players> findByDebutDateAfter(String afterDate, Pageable pageable);

    List<Players> findByCountry(String country, Sort sort);
}
