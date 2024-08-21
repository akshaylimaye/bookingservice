package com.adlier.booking.roomBookingService.service;

import com.adlier.booking.roomBookingService.dto.RoomDto;
import com.adlier.booking.roomBookingService.entity.Rooms;
import com.adlier.booking.roomBookingService.repository.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepo roomRepo;

    public  boolean addRoom(RoomDto roomDto) {

        try {
            Rooms room = new Rooms();
            room.setAvailable(true);
            room.setType(roomDto.getType());
            room.setPrice(roomDto.getPrice());
            room.setName(roomDto.getName());
            roomRepo.save(room);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }
}
