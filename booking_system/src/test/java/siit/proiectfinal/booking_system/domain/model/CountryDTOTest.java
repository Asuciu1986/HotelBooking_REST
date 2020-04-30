package siit.proiectfinal.booking_system.domain.model;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import siit.proiectfinal.booking_system.domain.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class CountryDTOTest {


    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void countryToCountryDTO(){

        Country country = new Country();
        country.setName("India");

        Integer id = country.getId();

        CountryDTO countryDTO = modelMapper.map(country,CountryDTO.class);

        assertEquals(id,countryDTO.getId());
        assertEquals(country.getName(),countryDTO.getName());
    }

}