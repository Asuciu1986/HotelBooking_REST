package siit.proiectfinal.booking_system.service;


import org.springframework.stereotype.Service;
import siit.proiectfinal.booking_system.domain.entity.Room;
import siit.proiectfinal.booking_system.repository.RoomRepository;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room getRoomById(int id){
        return roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found"));
    }

    public Room createRoom(Room room){
        return roomRepository.save(room);
    }
}
