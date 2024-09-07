package com.infy.repository;

import com.infy.entity.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
}
