package com.adlier.booking.roomBookingService.controller.customer;

import com.adlier.booking.roomBookingService.service.customer.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/room/{pageNumber}")
    public ResponseEntity<?> getAllAvailableRooms(@PathVariable int pageNumber) {
        return ResponseEntity.ok(roomService.getAllAvailableRooms(pageNumber));
    }
}
