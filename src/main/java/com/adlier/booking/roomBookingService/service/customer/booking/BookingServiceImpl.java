package com.adlier.booking.roomBookingService.service.customer.booking;


import com.adlier.booking.roomBookingService.dto.ReservationDto;
import com.adlier.booking.roomBookingService.entity.Reservation;
import com.adlier.booking.roomBookingService.entity.Rooms;
import com.adlier.booking.roomBookingService.entity.User;
import com.adlier.booking.roomBookingService.enums.ReservationStatus;
import com.adlier.booking.roomBookingService.repository.ReservationRepository;
import com.adlier.booking.roomBookingService.repository.RoomRepository;
import com.adlier.booking.roomBookingService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    private final ReservationRepository reservationRepository;

    public boolean saveReservation(ReservationDto reservationDto) {
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        Optional<Rooms> optionalRooms = roomRepository.findById(reservationDto.getRoomId());

        if(optionalUser.isPresent() && optionalUser.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setRooms(optionalRooms.get());
            reservation.setUser(optionalUser.get());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);

            int days = (int) ChronoUnit.DAYS.between(reservationDto.getCheckInDate(), reservationDto.getCheckOutDate());
            reservation.setPrice(optionalRooms.get().getPrice() * days);
            reservationRepository.save(reservation);
            return true;
        }
        return false;

    }
}
