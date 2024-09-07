package com.infy.repository;

import com.infy.entity.CabBooking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<CabBooking,Integer> {
    List<CabBooking> findByUserMobile(Long mobile);
}
