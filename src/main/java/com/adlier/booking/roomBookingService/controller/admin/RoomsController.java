package com.adlier.booking.roomBookingService.controller.admin;

import com.adlier.booking.roomBookingService.dto.RoomDto;
import com.adlier.booking.roomBookingService.dto.RoomsResponseDto;
import com.adlier.booking.roomBookingService.service.admin.RoomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class RoomsController {

    private final RoomsService service;

    @PostMapping("/room/add-room")
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

    @PutMapping("/room/{roomId}")
    public ResponseEntity updateRoom(@PathVariable int roomId, @RequestBody RoomDto roomDto) {
        boolean response = service.updateRoom(roomId, roomDto);
        if(response) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else  {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/room/{roomId}")
    public ResponseEntity deleteRoom(@PathVariable int roomId) {
        boolean response = service.deleteRoom(roomId);
        if(response) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else  {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
