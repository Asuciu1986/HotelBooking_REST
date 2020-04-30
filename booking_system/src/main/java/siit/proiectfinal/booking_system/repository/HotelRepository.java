package siit.proiectfinal.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siit.proiectfinal.booking_system.domain.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {

}
