package siit.proiectfinal.booking_system.controller;

import com.sun.javafx.collections.ImmutableObservableList;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import siit.proiectfinal.booking_system.domain.entity.*;
import siit.proiectfinal.booking_system.domain.mapper.RoomAvailabilityMapper;
import siit.proiectfinal.booking_system.domain.mapper.RoomAvailabilityUpdateMapper;
import siit.proiectfinal.booking_system.domain.mapper.RoomMapper;
import siit.proiectfinal.booking_system.domain.model.*;
import siit.proiectfinal.booking_system.exception.CustomerNotFoundException;
import siit.proiectfinal.booking_system.exception.RoomAvailabilityException;
import siit.proiectfinal.booking_system.service.RoomAvailabilityService;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/available_rooms")
public class RoomAvailabilityController {

    private final RoomAvailabilityService roomAvailabilityService;
    private final RoomAvailabilityMapper roomAvailabilityMapper;
    private final RoomMapper roomMapper;
    private final RoomAvailabilityUpdateMapper roomAvailabilityUpdateMapper;

    public RoomAvailabilityController(RoomAvailabilityService roomAvailabilityService, RoomAvailabilityMapper roomAvailabilityMapper, RoomMapper roomMapper, RoomAvailabilityUpdateMapper roomAvailabilityUpdateMapper) {
        this.roomAvailabilityService = roomAvailabilityService;
        this.roomAvailabilityMapper = roomAvailabilityMapper;
        this.roomMapper = roomMapper;
        this.roomAvailabilityUpdateMapper = roomAvailabilityUpdateMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoomAvailabilityDTO> getAllRoomAvailabilities() {

        return roomAvailabilityService.getAllAvailableRooms().stream()
                .map(roomAvailabilityMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomAvailabilityDTO getAvailableRoomById(@PathVariable(name = "id") int id) {

        return roomAvailabilityMapper.toDTO(roomAvailabilityService.getById(id));
    }

    @GetMapping("/filterDateCity")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomDTO> getAvailableRoomsByDateAndCity(@RequestParam(name = "reservationDate")
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationDate,
                                                        @RequestParam(name = "cityName") String cityName) {

        List<Room> availableRooms = roomAvailabilityService.getAvailableRoomsByDateAndCityName(reservationDate, cityName);
        return availableRooms.stream()
                .map(roomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomAvailabilityDTO createdRoomAvailability(@RequestBody RoomAvailabilityDTO roomAvailabilityDTO) {
        RoomAvailability roomAvailability = roomAvailabilityMapper.toEntity(roomAvailabilityDTO);
        RoomAvailability createdRoomAvailability = roomAvailabilityService.updateRoomAvailability(roomAvailability);
        return roomAvailabilityMapper.toDTO(createdRoomAvailability);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomAvailabilityDTO updateRoomAvailability(@PathVariable(name = "id") int id, @RequestBody RoomAvailabilityDTO roomAvailabilityDTO) {
        roomAvailabilityDTO.setId(id);
        RoomAvailability roomAvailability = roomAvailabilityMapper.toEntity(roomAvailabilityDTO);
        RoomAvailability updatedRoomAvailability = roomAvailabilityService.updateRoomAvailability(roomAvailability);
        return roomAvailabilityMapper.toDTO(updatedRoomAvailability);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomAvailabilityDTO updateRoomAvailability(@PathVariable(name = "id") int id, @RequestBody RoomAvailabilityUpdateDTO roomPatch) {

        roomPatch.setId(id);

        try {
            RoomAvailability roomAvailability1 = roomAvailabilityUpdateMapper.toEntity(roomPatch);
            RoomAvailability updatedRoomAvailability = roomAvailabilityService.partialUpdate(roomAvailability1);
            return roomAvailabilityMapper.toDTO(updatedRoomAvailability);
        } catch (RoomAvailabilityException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Roomavailability not found");
        }
    }

    @GetMapping("/filterDateCityPersonCapacity")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomDTO> getAvailableRoomsByDateCityAndPersonCapacity(@RequestParam(name = "reservationDate")
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationDate,
                                                                      @RequestParam(name = "cityName") String cityName,
                                                                      @RequestParam(name = "personCapacity") int personCapacity) {

        List<Room> availableRooms = roomAvailabilityService.getAvailableRoomsByDateCityNameAndPersonCapacity(reservationDate, cityName, personCapacity);
        return availableRooms.stream()
                .map(roomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("createList")
    @ResponseStatus(HttpStatus.CREATED)
    public List<RoomAvailabilityDTO> addAvailabilityForExistingRoomsInAnInterval(@RequestParam(name = "startDate")
                                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                                 @RequestParam(name = "endDate")
                                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<RoomAvailability> roomAvailabilitiesList = roomAvailabilityService.addAvailabilityToAllExistingRoomsByDateInterval(startDate, endDate);
        return roomAvailabilitiesList.stream().map(roomAvailabilityMapper::toDTO).collect(Collectors.toList());
    }

//    private RoomAvailability mapToEntity(RoomAvailabilityDTO roomAvailabilityDTO) throws ParseException {
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        RoomAvailability roomAvailability = modelMapper.typeMap(RoomAvailabilityDTO.class, RoomAvailability.class)
//                .addMappings(new PropertyMap<RoomAvailabilityDTO, RoomAvailability>() {
//                    @Override
//                    protected void configure() {
//                        map(RoomAvailabilityDTO::getRoomId,)
//                    }
//                });
//    }

}
