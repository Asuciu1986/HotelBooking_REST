package siit.proiectfinal.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siit.proiectfinal.booking_system.domain.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
}
