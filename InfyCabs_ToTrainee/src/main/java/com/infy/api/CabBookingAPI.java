package com.infy.api;

import com.infy.dto.CabBookingDTO;
import com.infy.exception.InfyCabException;
import com.infy.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bookings")
public class CabBookingAPI {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private Environment environment;

    @PostMapping(value = "/")
    public ResponseEntity<String> bookCab(@RequestBody CabBookingDTO cabBookingDTO) throws InfyCabException {
        Integer bookingId = bookingService.bookCab(cabBookingDTO);
        String successMessage = environment.getProperty("API.BOOKING_SUCCESSFUL") + bookingId;
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{mobileNo}")
    public ResponseEntity<List<CabBookingDTO>> getBookingDetails(@PathVariable Long mobileNo) throws InfyCabException {
        List<CabBookingDTO> cabBookingDTOS = bookingService.getBookingDetails(mobileNo);
        return new ResponseEntity<>(cabBookingDTOS, HttpStatus.OK);
    }

    @PutMapping(value = "{bookingId}")
    public ResponseEntity<String> cancleBooking(@PathVariable Integer bookingId) throws InfyCabException {
        bookingService.cancleBooking(bookingId);
        String successMessage = environment.getProperty("API.BOOKING_CANCELLED");
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }
}
