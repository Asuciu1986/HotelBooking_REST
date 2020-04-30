package siit.proiectfinal.booking_system.service;

import org.springframework.stereotype.Service;
import siit.proiectfinal.booking_system.domain.entity.*;
import siit.proiectfinal.booking_system.repository.CityRepository;
import siit.proiectfinal.booking_system.repository.HotelRepository;
import siit.proiectfinal.booking_system.repository.RoomAvailabilityRepository;
import siit.proiectfinal.booking_system.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomAvailabilityService {

    private final RoomAvailabilityRepository roomAvailabilityRepository;
    private final RoomRepository roomRepository;
    private final CityRepository cityRepository;
    private final HotelRepository hotelRepository;

    public RoomAvailabilityService(RoomAvailabilityRepository roomAvailabilityRepository, RoomRepository roomRepository, CityRepository cityRepository, HotelRepository hotelRepository) {
        this.roomAvailabilityRepository = roomAvailabilityRepository;
        this.roomRepository = roomRepository;
        this.cityRepository = cityRepository;
        this.hotelRepository = hotelRepository;
    }

    public List<Room> getAvailableRoomsByDateAndCityName(LocalDate providedDate, String cityName){

        City city = cityRepository.findByName(cityName);
        List<Hotel> hotels = city.getHotels();

        return roomAvailabilityRepository.findAllByReservationDateAndRoomAvailabilityStatus(providedDate, RoomAvailabilityStatus.AVAILABLE).stream()
                .map(RoomAvailability::getRoom)
                .collect(Collectors.toList());
    }

    public RoomAvailability getById(int id){
        return roomAvailabilityRepository.findById(id).orElse(null);
    }
}
