package com.adlier.booking.roomBookingService.service.admin;

import aj.org.objectweb.asm.commons.InstructionAdapter;
import com.adlier.booking.roomBookingService.dto.ReservationDto;
import com.adlier.booking.roomBookingService.dto.ReservationResponseDto;
import com.adlier.booking.roomBookingService.entity.Reservation;
import com.adlier.booking.roomBookingService.entity.Rooms;
import com.adlier.booking.roomBookingService.enums.ReservationStatus;
import com.adlier.booking.roomBookingService.repository.ReservationRepository;
import com.adlier.booking.roomBookingService.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    private final RoomRepository roomRepository;

    public ReservationResponseDto getAllReservations(int pageNumber) {

        List<ReservationDto> reservationDtoList = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNumber, 4);

        Page<Reservation> reservationPage = reservationRepository.findAll(pageable);

        ReservationResponseDto responseDto = new ReservationResponseDto();

        responseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        responseDto.setTotalPages(reservationPage.getTotalPages());

        for (Reservation reservation: reservationPage) {
            reservationDtoList.add(reservation.getReservationDto());
        }
        responseDto.setReservationDtoList(reservationDtoList);
        return  responseDto;

    }

    public boolean updateReservationStatus(int id, String status) {

        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            if("APPROVED".equals(status)) {
                reservation.setReservationStatus(ReservationStatus.APPROVED);

                Rooms room = reservation.getRooms();
                room.setAvailable(false);
                roomRepository.save(room);
            } else {
                reservation.setReservationStatus(ReservationStatus.REJECTED);
            }
            reservationRepository.save(reservation);




            return true;
        }
        return false;
    }
}
