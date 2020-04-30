package siit.proiectfinal.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siit.proiectfinal.booking_system.domain.entity.City;

public interface CityRepository extends JpaRepository<City,Integer> {

    City findByName(String name);
}
