package siit.proiectfinal.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siit.proiectfinal.booking_system.domain.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
