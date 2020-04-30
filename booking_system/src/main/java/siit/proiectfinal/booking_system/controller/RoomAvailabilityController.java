package siit.proiectfinal.booking_system.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siit.proiectfinal.booking_system.domain.entity.City;
import siit.proiectfinal.booking_system.domain.entity.Room;
import siit.proiectfinal.booking_system.domain.entity.RoomAvailability;
import siit.proiectfinal.booking_system.domain.model.RoomAvailabilityDTO;
import siit.proiectfinal.booking_system.domain.model.RoomDTO;
import siit.proiectfinal.booking_system.service.RoomAvailabilityService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/available_rooms")
public class RoomAvailabilityController {

    private final RoomAvailabilityService roomAvailabilityService;

    @Autowired
    ModelMapper modelMapper;

    public RoomAvailabilityController(RoomAvailabilityService roomAvailabilityService) {
        this.roomAvailabilityService = roomAvailabilityService;
    }

    @GetMapping("/{id}")
    public RoomAvailability getAvailableRooms(@PathVariable(name = "id") int id){

        return roomAvailabilityService.getById(id);
    }

    @GetMapping("/{reservationDate}/{cityName}")
    public List<RoomDTO> getAvailableRooms(@PathVariable(name = "reservationDate") LocalDate reservationDate, @PathVariable(name = "cityName") String cityName){

        List<Room> availableRooms = roomAvailabilityService.getAvailableRoomsByDateAndCityName(reservationDate,cityName);
        return availableRooms.stream()
                .map(x -> modelMapper.map(x,RoomDTO.class))
                .collect(Collectors.toList());
    }
}
