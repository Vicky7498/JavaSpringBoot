package com.infy.api;

import com.infy.dto.BookingDTO;
import com.infy.exception.InfyCourierException;
import com.infy.service.BookingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/infycourier")
@Validated
public class CourierBookingAPI {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private Environment environment;

    @PostMapping(value = "/courier")
    public ResponseEntity<String> bookCourier(@Valid @RequestBody BookingDTO bookingDTO) throws InfyCourierException {
        try {
            Integer courierBookingId = bookingService.bookCourier(bookingDTO);
            String successMessage = environment.getProperty("API.BOOKING_SUCCESS") + courierBookingId;
            return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, environment.getProperty(exception.getMessage()), exception);
        }
    }

    @GetMapping(value = "/courier/{bookingId}")
    public ResponseEntity<BookingDTO> getCourierDetails(@PathVariable Integer bookingId) throws InfyCourierException {
        try {
            BookingDTO bookingDTO = bookingService.getCourierDetail(bookingId);
            return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
        }
    }
}
