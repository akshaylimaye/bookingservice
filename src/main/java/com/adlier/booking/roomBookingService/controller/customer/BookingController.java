package com.adlier.booking.roomBookingService.controller.customer;

import com.adlier.booking.roomBookingService.dto.ReservationDto;
import com.adlier.booking.roomBookingService.service.customer.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class BookingController {


    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> saveBooking(@RequestBody ReservationDto reservationDto) {
        boolean isReserved = bookingService.saveReservation(reservationDto);

        if (isReserved) {
          return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
