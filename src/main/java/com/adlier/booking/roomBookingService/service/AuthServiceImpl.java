package com.adlier.booking.roomBookingService.service;

import com.adlier.booking.roomBookingService.dto.SignupDto;
import com.adlier.booking.roomBookingService.dto.UserDto;
import com.adlier.booking.roomBookingService.entity.User;
import com.adlier.booking.roomBookingService.enums.UserRole;
import com.adlier.booking.roomBookingService.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    public void createAdminAccount() {

        User admin = userRepository.findByUserRole(UserRole.ADMIN);

        if(admin == null) {
            User user = new User();
            user.setEmail("admin@booking.com");
            user.setName("Admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
        } else {
            System.out.println("Admin already exist");
        }
    }

    public UserDto createUser(SignupDto signup) {

        if(userRepository.findByEmail(signup.getEmail()) != null) {
            throw  new EntityExistsException("Email already present- " + signup.getEmail());
        }

        User user = new User();
        user.setName(signup.getEmail());
        user.setUserRole(UserRole.CUSTOMER);
        user.setEmail(signup.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signup.getPassword()));
        User createdUser = userRepository.save(user);
        return createdUser.getUserDTO();
    }
}
