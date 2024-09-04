package com.adlier.booking.roomBookingService.service.customer.room;


import com.adlier.booking.roomBookingService.dto.RoomDto;
import com.adlier.booking.roomBookingService.dto.RoomsResponseDto;
import com.adlier.booking.roomBookingService.entity.Rooms;
import com.adlier.booking.roomBookingService.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public RoomsResponseDto getAllAvailableRooms(int pageNumber) {

        List<RoomDto> roomsList = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<Rooms> roomsPage =  roomRepository.findByAvailable(true, pageable);
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
