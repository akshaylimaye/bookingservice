package com.adlier.booking.roomBookingService.service;

import com.adlier.booking.roomBookingService.dto.RoomDto;
import com.adlier.booking.roomBookingService.dto.RoomsResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface RoomService {

    boolean addRoom(RoomDto roomDto);

     RoomsResponseDto getAllRooms(int pageNumber);

    RoomDto getRoomByID(int roomId);
}
