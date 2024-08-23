package com.adlier.booking.roomBookingService.entity;

import com.adlier.booking.roomBookingService.dto.RoomDto;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "tbl_room")
@Data
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    private String name;

    private String type;

    private int price;

    private boolean available;

    public RoomDto getRoomDto() {

        RoomDto roomDto = new RoomDto();
        roomDto.setRoomId(roomId);
        roomDto.setName(name);
        roomDto.setType(type);
        roomDto.setPrice(price);
        roomDto.setAvailable(available);
        return roomDto;
    }

}
