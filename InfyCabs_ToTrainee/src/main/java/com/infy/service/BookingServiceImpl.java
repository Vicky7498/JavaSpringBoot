package com.infy.service;

import com.infy.dto.CabBookingDTO;
import com.infy.entity.CabBooking;
import com.infy.exception.InfyCabException;
import com.infy.repository.BookingRepository;
import com.infy.repository.FareRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "bookingService")
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FareRepository fareRepository;

    @Override
    public Integer bookCab(CabBookingDTO cabBookingDTO) throws InfyCabException {
        Float fare = fareRepository.findFareBySourceAndDestination(cabBookingDTO.getSource(), cabBookingDTO.getDestination());
        if (fare == null) {
            throw new InfyCabException("BookingService.INVALID_SERVICE_AREA");
        }
        cabBookingDTO.setFare(fare);
        CabBooking cabBooking = new CabBooking();
        cabBooking.setDestination(cabBookingDTO.getDestination());
        cabBooking.setFare(cabBookingDTO.getFare());
        cabBooking.setSource(cabBookingDTO.getSource());
        cabBooking.setStatus(cabBookingDTO.getStatus());
        cabBooking.setTravelDate(cabBookingDTO.getTravelDate());
        cabBooking.setUserMobile(cabBooking.getUserMobile());
        return bookingRepository.save(cabBooking).getBookingId();
    }

    @Override
    public List<CabBookingDTO> getBookingDetails(Long mobile) throws InfyCabException {
        List<CabBookingDTO> cabBookingDTOS = new ArrayList<>();
        List<CabBooking> cabBookings = bookingRepository.findByUserMobile(mobile);
        if (cabBookings.isEmpty()) {
            throw new InfyCabException("BookingService.BOOKINGS_NOT_FOUND");
        }
        cabBookings.forEach(cabBooking -> {
            CabBookingDTO cabBookingDTO = new CabBookingDTO();
            cabBookingDTO.setBookingId(cabBooking.getBookingId());
            cabBookingDTO.setDestination(cabBooking.getDestination());
            cabBookingDTO.setSource(cabBooking.getSource());
            cabBookingDTO.setFare(cabBooking.getFare());
            cabBookingDTO.setStatus(cabBooking.getStatus('C'));
            cabBookingDTO.setTravelDate(cabBooking.getTravelDate());
            cabBookingDTO.setUserMobile(cabBooking.getUserMobile());
            cabBookingDTOS.add(cabBookingDTO);
        });
        return cabBookingDTOS;
    }

    @Override
    public Integer cancleBooking(Integer bookingId) throws InfyCabException {
        Optional<CabBooking> cabBooking = bookingRepository.findById(bookingId);
        cabBooking.orElseThrow(() -> new InfyCabException("BookingService.BOOKINGS_NOT_FOUND"));
        cabBooking.get().setStatus('C');
        return cabBooking.get().getBookingId();
    }
}
