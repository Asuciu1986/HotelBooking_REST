package siit.proiectfinal.booking_system.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import siit.proiectfinal.booking_system.domain.entity.Room;
import siit.proiectfinal.booking_system.domain.mapper.RoomMapper;
import siit.proiectfinal.booking_system.domain.model.RoomDTO;
import siit.proiectfinal.booking_system.repository.RoomRepository;
import siit.proiectfinal.booking_system.service.RoomService;


@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final RoomMapper roomMapper;

    public RoomController(RoomService roomService, RoomMapper roomMapper) {
        this.roomService = roomService;
        this.roomMapper = roomMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomDTO getRoomById(@PathVariable(name = "id") int id){
        Room room = roomService.getRoomById(id);
        return roomMapper.toDTO(room);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDTO addRoom(@RequestBody RoomDTO roomDTO){
        Room room = roomMapper.toEntity(roomDTO);
        return roomMapper.toDTO(roomService.createRoom(room));
    }




}
