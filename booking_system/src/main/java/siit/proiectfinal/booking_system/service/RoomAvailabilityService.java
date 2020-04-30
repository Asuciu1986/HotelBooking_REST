package siit.proiectfinal.booking_system.service;

import org.springframework.stereotype.Service;
import siit.proiectfinal.booking_system.domain.entity.Room;
import siit.proiectfinal.booking_system.domain.entity.RoomAvailability;
import siit.proiectfinal.booking_system.domain.entity.RoomAvailabilityStatus;
import siit.proiectfinal.booking_system.repository.CityRepository;
import siit.proiectfinal.booking_system.repository.HotelRepository;
import siit.proiectfinal.booking_system.repository.RoomAvailabilityRepository;
import siit.proiectfinal.booking_system.repository.RoomRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<RoomAvailability> getAllAvailableRooms() {
        return roomAvailabilityRepository.findAll();
    }

    public List<Room> getAvailableRoomsByDateAndCityName(LocalDate providedDate, String cityName) {

        return roomAvailabilityRepository.findAllByReservationDateAndRoomAvailabilityStatus(providedDate, RoomAvailabilityStatus.AVAILABLE).stream()
                .map(RoomAvailability::getRoom)
                .filter(x -> cityName.equals(x.getHotel().getCity().getName()))
                .collect(Collectors.toList());
    }

    public List<Room> getAvailableRoomsByDateCityNameAndPersonCapacity(LocalDate providedDate, String cityName, int personCapacity) {

        return roomAvailabilityRepository.findAllByReservationDateAndRoomAvailabilityStatus(providedDate, RoomAvailabilityStatus.AVAILABLE).stream()
                .map(RoomAvailability::getRoom)
                .filter(x -> cityName.equals(x.getHotel().getCity().getName()))
                .filter(x -> x.getPersonCapacity() == personCapacity)
                .collect(Collectors.toList());
    }

    public RoomAvailability getById(int id) {
        return roomAvailabilityRepository.findById(id).orElse(null);
    }

    public RoomAvailability updateRoomAvailability(RoomAvailability roomAvailability) {
        return roomAvailabilityRepository.save(roomAvailability);
    }

    //only for testing purposes
    public List<RoomAvailability> addAvailabilityToAllExistingRoomsByDateInterval(LocalDate startDate, LocalDate endDate) {

        List<Room> allRooms = roomRepository.findAll();
        List<LocalDate> dates = new ArrayList<>();

        LocalDate currentDate = startDate;
        while (currentDate.isBefore(endDate) || currentDate.equals(endDate)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        for (LocalDate date : dates) {
            for (Room room : allRooms
            ) {
                RoomAvailability roomAvailability = new RoomAvailability();
                roomAvailability.setRoom(room);
                roomAvailability.setRentPricePerNight(new BigDecimal("100.00"));
                roomAvailability.setReservationDate(date);
                roomAvailability.setRoomAvailabilityStatus(RoomAvailabilityStatus.AVAILABLE);
                roomAvailabilityRepository.save(roomAvailability);
            }
        }
        return roomAvailabilityRepository.findAllByReservationDateBetween(startDate, endDate);
    }
}
