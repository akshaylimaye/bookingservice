package com.adlier.booking.roomBookingService.service;

import com.adlier.booking.roomBookingService.dto.RoomDto;
import org.springframework.stereotype.Service;

public interface RoomService {

    boolean addRoom(RoomDto roomDto);
}
