package com.adlier.booking.roomBookingService.repository;

import com.adlier.booking.roomBookingService.entity.User;
import com.adlier.booking.roomBookingService.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByUserRole(UserRole role);
}
