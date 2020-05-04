package siit.proiectfinal.booking_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import siit.proiectfinal.booking_system.domain.entity.Hotel;
import siit.proiectfinal.booking_system.domain.mapper.HotelMapper;
import siit.proiectfinal.booking_system.domain.model.HotelDTO;
import siit.proiectfinal.booking_system.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    public final HotelService hotelService;
    public final HotelMapper hotelMapper;

    public HotelController(HotelService hotelService, HotelMapper hotelMapper) {
        this.hotelService = hotelService;
        this.hotelMapper = hotelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HotelDTO getHotelById(@PathVariable(name = "id") int id){
        Hotel hotel = hotelService.getHotelByid(id);
        return hotelMapper.toDTO(hotel);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelDTO addHotel(@RequestBody HotelDTO hotelDTO){
        Hotel hotel = hotelMapper.toEntity(hotelDTO);
        return hotelMapper.toDTO(hotelService.createHotel(hotel));
    }

}
