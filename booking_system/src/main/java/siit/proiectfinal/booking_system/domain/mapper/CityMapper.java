package siit.proiectfinal.booking_system.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import siit.proiectfinal.booking_system.domain.entity.City;
import siit.proiectfinal.booking_system.domain.entity.Country;
import siit.proiectfinal.booking_system.domain.model.CityDTO;

@Mapper(uses = {CountryMapper.class})
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mapping(source = "country.id", target = "countryId")
    CityDTO toDTO(City city);

    @Mapping(source = "countryId", target = "country.id")
    @Mapping(target = "hotels", ignore = true)
    City toEntity(CityDTO cityDTO);


}
