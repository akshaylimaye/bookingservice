package com.adlier.booking.roomBookingService.dto;

import com.adlier.booking.roomBookingService.enums.ReservationStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDto {

    private int id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int price;

    private ReservationStatus reservationStatus;

    private int roomId;

    private String roomType;

    private String roomName;

    private int userId;

    private String userName;
}
