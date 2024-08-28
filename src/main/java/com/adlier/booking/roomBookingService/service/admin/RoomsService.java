package com.adlier.booking.roomBookingService.service.admin;

import com.adlier.booking.roomBookingService.dto.RoomDto;
import com.adlier.booking.roomBookingService.dto.RoomsResponseDto;


public interface RoomsService {

    boolean addRoom(RoomDto roomDto);

     RoomsResponseDto getAllRooms(int pageNumber);

    RoomDto getRoomByID(int roomId);

    boolean updateRoom(int id, RoomDto roomDto);

    boolean deleteRoom(int roomId);
}
