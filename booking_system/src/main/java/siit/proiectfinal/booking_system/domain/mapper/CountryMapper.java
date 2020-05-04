package siit.proiectfinal.booking_system.domain.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import siit.proiectfinal.booking_system.domain.entity.Country;
import siit.proiectfinal.booking_system.domain.model.CountryDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CountryMapper {

    CountryDTO toDTO(Country country);

    @Mapping(target = "cities", ignore = true)
    Country toEntity(CountryDTO countryDTO);

}
