package siit.proiectfinal.booking_system.domain.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import siit.proiectfinal.booking_system.domain.entity.RoomAvailability;
import siit.proiectfinal.booking_system.domain.model.RoomAvailabilityUpdateDTO;


@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoomAvailabilityUpdateMapper {

    RoomAvailabilityUpdateDTO toDTO(RoomAvailability roomAvailability);

    @Mapping(target = "room", ignore = true)
    @Mapping(target = "reservationDate", ignore = true)
    RoomAvailability toEntity(RoomAvailabilityUpdateDTO roomAvailabilityUpdateDTO);
}


