package com.adlier.booking.roomBookingService.service.admin;

import com.adlier.booking.roomBookingService.dto.RoomDto;
import com.adlier.booking.roomBookingService.dto.RoomsResponseDto;
import com.adlier.booking.roomBookingService.entity.Rooms;
import com.adlier.booking.roomBookingService.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService {

    private final RoomRepository roomRepository;

    public  boolean addRoom(RoomDto roomDto) {

        try {
            Rooms room = new Rooms();
            room.setAvailable(true);
            room.setType(roomDto.getType());
            room.setPrice(roomDto.getPrice());
            room.setName(roomDto.getName());
            roomRepository.save(room);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    @Override
    public RoomsResponseDto getAllRooms(int pageNumber) {

        List<RoomDto> roomsList = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNumber, 6);
        List<Rooms> rooms = roomRepository.findAll();
        Page<Rooms> roomsPage =  roomRepository.findAll(pageable);
        RoomsResponseDto responseDto = new RoomsResponseDto();
        responseDto.setPageNumber(roomsPage.getPageable().getPageNumber());
        responseDto.setTotalPages(roomsPage.getTotalPages());

        for (Rooms room: roomsPage) {
            roomsList.add(room.getRoomDto());
        }
        responseDto.setRoomList(roomsList);
        return  responseDto;
    }

    @Override
    public RoomDto getRoomByID(int roomId) {
        Optional<Rooms> room = roomRepository.findById(roomId);
        if(room.isPresent()) {
            return room.get().getRoomDto();
        } else {
          throw new EntityNotFoundException("Room doesn't exist");
        }

    }

    @Override
    public boolean updateRoom(int id, RoomDto roomDto) {
        Optional<Rooms> room = roomRepository.findById(id);
        if(room.isPresent()) {
            Rooms existingRoom = room.get();

            existingRoom.setName(roomDto.getName());
            existingRoom.setType(roomDto.getType());
            existingRoom.setPrice(roomDto.getPrice());
            existingRoom.setAvailable(roomDto.isAvailable());
            roomRepository.save(existingRoom);
            return true;
        } else {
            throw new EntityNotFoundException("Room doesn't exist");
        }
    }

    @Override
    public boolean deleteRoom(int roomId) {
        // TODO: 28/08/24   Delete as in change status to 0
        Optional<Rooms> room = roomRepository.findById(roomId);
        if(room.isPresent()) {
            roomRepository.deleteById(roomId);
            return true;
        } else {
            throw new EntityNotFoundException("Room doesn't exist");
        }
    }
}
