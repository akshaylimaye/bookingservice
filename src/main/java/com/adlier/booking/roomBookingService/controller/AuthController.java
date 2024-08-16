package com.adlier.booking.roomBookingService.controller;

import com.adlier.booking.roomBookingService.dto.SignupDto;
import com.adlier.booking.roomBookingService.dto.UserDto;
import com.adlier.booking.roomBookingService.service.AuthService;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signupUser(@RequestBody SignupDto signupDto) {

        try {
            UserDto user = authService.createUser(signupDto);
            return new ResponseEntity(user, HttpStatus.CREATED);

        } catch (EntityExistsException e) {
            return new ResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity("User not created", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }








}
