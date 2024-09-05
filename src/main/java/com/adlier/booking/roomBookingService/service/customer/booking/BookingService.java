package com.adlier.booking.roomBookingService.service.customer.booking;

import com.adlier.booking.roomBookingService.dto.ReservationDto;
import com.adlier.booking.roomBookingService.dto.ReservationResponseDto;

public interface BookingService {

    boolean saveReservation(ReservationDto reservationDto);

    ReservationResponseDto getAllReservationsByUserId (int userId, int pageNumber);
}
