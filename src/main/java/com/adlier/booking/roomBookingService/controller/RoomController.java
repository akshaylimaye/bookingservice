package com.adlier.booking.roomBookingService.controller;

import com.adlier.booking.roomBookingService.dto.RoomDto;
import com.adlier.booking.roomBookingService.dto.RoomsResponseDto;
import com.adlier.booking.roomBookingService.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService service;

    @PostMapping("/admin/add-room")
    public ResponseEntity addRoom(@RequestBody RoomDto roomDto) {
        boolean isAdded = service.addRoom(roomDto);
        if(isAdded) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @GetMapping("/rooms/{pageNumber}")
    public ResponseEntity getAllRooms(@PathVariable int pageNumber) {
        RoomsResponseDto roomsList = service.getAllRooms(pageNumber);
        return new ResponseEntity(roomsList, HttpStatus.OK);
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity getRoomById(@PathVariable int roomId) {
        RoomDto room = service.getRoomByID(roomId);
        return new ResponseEntity(room, HttpStatus.OK);
    }

}
