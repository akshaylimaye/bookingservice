package com.adlier.booking.roomBookingService.controller;

import com.adlier.booking.roomBookingService.dto.AuthenticationRequest;
import com.adlier.booking.roomBookingService.dto.AuthenticationResponse;
import com.adlier.booking.roomBookingService.dto.SignupDto;
import com.adlier.booking.roomBookingService.dto.UserDto;
import com.adlier.booking.roomBookingService.entity.User;
import com.adlier.booking.roomBookingService.repository.UserRepository;
import com.adlier.booking.roomBookingService.service.AuthService;
import com.adlier.booking.roomBookingService.service.UserService;
import com.adlier.booking.roomBookingService.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    private final UserService userService;

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

    @PostMapping("/login")
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(), authenticationRequest.getPassword()
            ));

        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        User user = userRepository.findByEmail(userDetails.getUsername());
        final String jwtToken = jwtUtil.generateToken(userDetails);


        authenticationResponse.setJwt(jwtToken);
        authenticationResponse.setUserRole(user.getUserRole());
        authenticationResponse.setUserId(user.getId());
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Incorrect username or password");
        } catch (Exception ex) {
            //System.out.println("Exception ex" + ex);
        }
        return  authenticationResponse;
    }








}
