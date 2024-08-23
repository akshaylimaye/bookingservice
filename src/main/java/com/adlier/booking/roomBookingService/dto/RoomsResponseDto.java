package com.adlier.booking.roomBookingService.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomsResponseDto {

    private List<RoomDto> roomList;

    private Integer totalPages;

    private Integer pageNumber;

}
