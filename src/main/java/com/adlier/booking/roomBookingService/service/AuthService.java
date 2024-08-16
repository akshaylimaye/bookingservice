package com.adlier.booking.roomBookingService.service;

import com.adlier.booking.roomBookingService.dto.SignupDto;
import com.adlier.booking.roomBookingService.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignupDto signupDto);
}
