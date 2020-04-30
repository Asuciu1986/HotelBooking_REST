package siit.proiectfinal.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siit.proiectfinal.booking_system.domain.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {


}
