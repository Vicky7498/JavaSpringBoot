package com.infy.repository;

import com.infy.entity.FareEstimation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FareRepository extends CrudRepository<FareEstimation, Integer> {
    @Query("select f.fare from FareEstimation f where f.source=:source and f.destination=:destination")
    Float findFareBySourceAndDestination(@Param("source") String source, @Param("destination") String destination);
}
