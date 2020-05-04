package siit.proiectfinal.booking_system.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siit.proiectfinal.booking_system.domain.entity.Room;
import siit.proiectfinal.booking_system.domain.model.RoomDTO;
import siit.proiectfinal.booking_system.repository.RoomRepository;
import siit.proiectfinal.booking_system.service.RoomService;


@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final ModelMapper modelMapper;

    public RoomController(RoomService roomService, ModelMapper modelMapper) {
        this.roomService = roomService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public RoomDTO getRoomById(@PathVariable(name = "id") int id){
        Room room = roomService.getRoomById(id);
        return modelMapper.map(room,RoomDTO.class);
    }



}
