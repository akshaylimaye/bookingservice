package com.adlier.booking.roomBookingService.service;

import com.adlier.booking.roomBookingService.dto.RoomDto;
import com.adlier.booking.roomBookingService.dto.RoomsResponseDto;
import com.adlier.booking.roomBookingService.entity.Rooms;
import com.adlier.booking.roomBookingService.repository.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public RoomsResponseDto getAllRooms(int pageNumber) {

        List<RoomDto> roomsList = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNumber, 6);
        List<Rooms> rooms = roomRepo.findAll();
        Page<Rooms> roomsPage =  roomRepo.findAll(pageable);
        RoomsResponseDto responseDto = new RoomsResponseDto();
        responseDto.setPageNumber(roomsPage.getPageable().getPageNumber());
        responseDto.setTotalPages(roomsPage.getTotalPages());

        for (Rooms room: roomsPage) {
            roomsList.add(room.getRoomDto());
        }
        responseDto.setRoomList(roomsList);
        return  responseDto;
    }
}
