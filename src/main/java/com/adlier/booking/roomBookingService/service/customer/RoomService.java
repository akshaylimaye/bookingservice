package com.adlier.booking.roomBookingService.service.customer;

import com.adlier.booking.roomBookingService.dto.RoomsResponseDto;

public interface RoomService {

    RoomsResponseDto getAllAvailableRooms(int pageNumber);
}
