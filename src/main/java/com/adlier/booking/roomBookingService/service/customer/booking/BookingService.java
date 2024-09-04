package com.adlier.booking.roomBookingService.service.customer.booking;

import com.adlier.booking.roomBookingService.dto.ReservationDto;

public interface BookingService {

    boolean saveReservation(ReservationDto reservationDto);
}
