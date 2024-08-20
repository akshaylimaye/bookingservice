package com.adlier.booking.roomBookingService.dto;

import com.adlier.booking.roomBookingService.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private int userId;

    private UserRole userRole;

}
