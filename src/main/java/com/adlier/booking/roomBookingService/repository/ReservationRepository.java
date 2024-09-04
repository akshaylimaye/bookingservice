package com.adlier.booking.roomBookingService.repository;

import com.adlier.booking.roomBookingService.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
