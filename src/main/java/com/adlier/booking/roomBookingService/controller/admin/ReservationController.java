package com.adlier.booking.roomBookingService.controller.admin;

import com.adlier.booking.roomBookingService.service.admin.ReservationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservation/{pageNumber}")
    public ResponseEntity<?> getAllReservations(@PathVariable int pageNumber) {
        try {
            return ResponseEntity.ok(reservationService.getAllReservations(pageNumber));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
