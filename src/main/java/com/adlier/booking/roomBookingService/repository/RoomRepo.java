package com.adlier.booking.roomBookingService.repository;

import com.adlier.booking.roomBookingService.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Rooms, Integer> {

}