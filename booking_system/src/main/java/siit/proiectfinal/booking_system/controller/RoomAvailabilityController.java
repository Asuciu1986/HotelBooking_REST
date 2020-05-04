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

    @Autowired
    ModelMapper modelMapper;

    public RoomAvailabilityController(RoomAvailabilityService roomAvailabilityService) {
        this.roomAvailabilityService = roomAvailabilityService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoomAvailabilityDTO> getAllRoomAvailabilities() {

        return roomAvailabilityService.getAllAvailableRooms().stream()
                .map(x -> modelMapper.map(x, RoomAvailabilityDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomAvailabilityDTO getAvailableRoomById(@PathVariable(name = "id") int id) {

        return modelMapper.map(roomAvailabilityService.getById(id), RoomAvailabilityDTO.class);
    }

    @GetMapping("/filterDateCity")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomDTO> getAvailableRoomsByDateAndCity(@RequestParam(name = "reservationDate")
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationDate,
                                                        @RequestParam(name = "cityName") String cityName) {

        List<Room> availableRooms = roomAvailabilityService.getAvailableRoomsByDateAndCityName(reservationDate, cityName);
        return availableRooms.stream()
                .map(x -> modelMapper.map(x, RoomDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RoomAvailabilityDTO createdRoomAvailability(@RequestBody RoomAvailabilityDTO roomAvailabilityDTO) {
        RoomAvailability roomAvailability = modelMapper.map(roomAvailabilityDTO, RoomAvailability.class);
        RoomAvailability createdRoomAvailability = roomAvailabilityService.updateRoomAvailability(roomAvailability);
        return modelMapper.map(createdRoomAvailability, RoomAvailabilityDTO.class);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomAvailabilityDTO updateRoomAvailability(@PathVariable(name = "id") int id, @RequestBody RoomAvailabilityDTO roomAvailabilityDTO) {
        roomAvailabilityDTO.setId(id);
        RoomAvailability roomAvailability = modelMapper.map(roomAvailabilityDTO, RoomAvailability.class);
        RoomAvailability updatedRoomAvailability = roomAvailabilityService.updateRoomAvailability(roomAvailability);
        return modelMapper.map(updatedRoomAvailability, RoomAvailabilityDTO.class);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomAvailabilityDTO updateRoomAvailability(@PathVariable(name = "id") int id, @RequestBody RoomAvailabilityUpdateDTO roomPatch) {

        roomPatch.setId(id);

        try {
            RoomAvailability roomAvailability = roomAvailabilityService.getById(id);
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(roomPatch, roomAvailability);
            RoomAvailability updatedRoomAvailability = roomAvailabilityService.updateRoomAvailability(roomAvailability);
            return modelMapper.map(updatedRoomAvailability, RoomAvailabilityDTO.class);
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
                .map(x -> modelMapper.map(x, RoomDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("createList")
    @ResponseStatus(HttpStatus.CREATED)
    public List<RoomAvailabilityDTO> addAvailabilityForExistingRoomsInAnInterval(@RequestParam(name = "startDate")
                                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                                 @RequestParam(name = "endDate")
                                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<RoomAvailability> roomAvailabilitiesList = roomAvailabilityService.addAvailabilityToAllExistingRoomsByDateInterval(startDate, endDate);
        return roomAvailabilitiesList.stream().map(x -> modelMapper.map(x, RoomAvailabilityDTO.class)).collect(Collectors.toList());
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


    private CountryDTO convertToDto(Country country) {
        return modelMapper.map(country, CountryDTO.class);
    }

    private Country convertToEntity(CountryDTO countryDTO) throws ParseException {
        Country country = modelMapper.map(countryDTO, Country.class);
        return country;
    }


}
