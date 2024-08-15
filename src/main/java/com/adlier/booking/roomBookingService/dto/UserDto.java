package com.adlier.booking.roomBookingService.dto;

import com.adlier.booking.roomBookingService.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private int id;

    private String email;

    private  String name;

    private UserRole userRole;
}
