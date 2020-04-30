package siit.proiectfinal.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siit.proiectfinal.booking_system.domain.entity.RoomAvailability;
import siit.proiectfinal.booking_system.domain.entity.RoomAvailabilityStatus;

import java.time.LocalDate;
import java.util.List;

public interface RoomAvailabilityRepository extends JpaRepository<RoomAvailability,Integer> {

    List<RoomAvailability> findAllByReservationDateAndRoomAvailabilityStatus(LocalDate reservationDate, RoomAvailabilityStatus roomAvailabilityStatus);
}
