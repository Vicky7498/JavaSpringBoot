package com.infy.service;

import com.infy.dto.CabBookingDTO;
import com.infy.exception.InfyCabException;

import java.util.List;

public interface BookingService {
    public Integer bookCab(CabBookingDTO cabBookingDTO) throws InfyCabException;

    public List<CabBookingDTO> getBookingDetails(Long mobile) throws InfyCabException;

    public Integer cancleBooking(Integer bookingId) throws InfyCabException;
}
