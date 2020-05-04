package siit.proiectfinal.booking_system.domain.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import siit.proiectfinal.booking_system.domain.entity.Hotel;
import siit.proiectfinal.booking_system.domain.model.HotelDTO;

@Mapper(componentModel = "spring", uses={CityMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface HotelMapper {

//    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    @Mapping(source = "city.id", target = "cityId")
    HotelDTO toDTO(Hotel hotel);


    @Mapping(source = "cityId", target = "city.id")
    @Mapping(target = "rooms", ignore = true)
    Hotel toEntity(HotelDTO hotelDTO);
}
