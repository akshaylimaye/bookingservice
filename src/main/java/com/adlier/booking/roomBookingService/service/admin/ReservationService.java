package com.adlier.booking.roomBookingService.service.admin;

import com.adlier.booking.roomBookingService.dto.ReservationResponseDto;

public interface ReservationService {

    ReservationResponseDto getAllReservations(int pageNumber);
}
