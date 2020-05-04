package siit.proiectfinal.booking_system.domain.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import siit.proiectfinal.booking_system.domain.entity.Hotel;
import siit.proiectfinal.booking_system.domain.entity.RoomAvailability;
import siit.proiectfinal.booking_system.domain.model.HotelDTO;
import siit.proiectfinal.booking_system.domain.model.RoomAvailabilityDTO;

@Mapper(componentModel = "spring",
        uses = {RoomMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoomAvailabilityMapper {

    @Mapping(source = "room.id", target = "roomId")
    RoomAvailabilityDTO toDTO(RoomAvailability roomAvailability);


    @Mapping(source = "roomId", target = "room.id")
    RoomAvailability toEntity(RoomAvailabilityDTO roomAvailabilityDTO);
}
