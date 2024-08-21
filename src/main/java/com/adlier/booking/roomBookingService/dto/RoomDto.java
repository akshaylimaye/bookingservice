package com.adlier.booking.roomBookingService.dto;

import lombok.Data;

@Data
public class RoomDto {

    private int roomId;

    private String name;

    private String type;

    private int price;

    private boolean available;
}
