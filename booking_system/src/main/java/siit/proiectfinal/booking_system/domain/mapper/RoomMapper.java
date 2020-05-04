package siit.proiectfinal.booking_system.domain.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import siit.proiectfinal.booking_system.domain.entity.Room;
import siit.proiectfinal.booking_system.domain.model.HotelDTO;
import siit.proiectfinal.booking_system.domain.model.RoomDTO;

@Mapper(componentModel = "spring",uses = {HotelMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RoomMapper {

//    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @Mapping(source = "hotel.id", target = "hotelId")
    RoomDTO toDTO(Room room);

    @Mapping(target = "hotel", source = "")
    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(target = "availabilities", ignore = true)
    Room toEntity(RoomDTO bookDTO);

    default Room fromId(final Integer id) {

        if (id == null) {
            return null;
        }

        final Room room=new Room();
        room.setId(id);

        return room;
    }

}
