package siit.proiectfinal.booking_system.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import siit.proiectfinal.booking_system.domain.entity.City;
import siit.proiectfinal.booking_system.domain.entity.Country;
import siit.proiectfinal.booking_system.domain.entity.Hotel;
import siit.proiectfinal.booking_system.domain.entity.Room;
import siit.proiectfinal.booking_system.repository.*;

import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CustomerRepository customerRepository;
    private final HotelRepository hotelRepository;
    private final ReservationRepository reservationRepository;
    private final RoomAvailabilityRepository roomAvailabilityRepository;
    private final RoomRepository roomRepository;

    public Bootstrap(CityRepository cityRepository, CountryRepository countryRepository, CustomerRepository customerRepository, HotelRepository hotelRepository, ReservationRepository reservationRepository, RoomAvailabilityRepository roomAvailabilityRepository, RoomRepository roomRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.customerRepository = customerRepository;
        this.hotelRepository = hotelRepository;
        this.reservationRepository = reservationRepository;
        this.roomAvailabilityRepository = roomAvailabilityRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) throws Exception{

        enterData();
        City city = cityRepository.findByName("Barcelona");
        System.out.println(city.getId() + ":" + city.getName());
        List<Hotel> hotels = city.getHotels();
        for (Hotel hotel:hotels){
            System.out.println(hotel.getId() + ":" + hotel.getName());
        }



    }

    private void enterData(){


        try{
            Country country1 = new Country();
            country1.setName("Spain");
            Country country2 = new Country();
            country2.setName("Greece");
            Country country3 = new Country();
            country3.setName("Belgium");
            //System.out.println(country1.getId());
            countryRepository.save(country1);
            countryRepository.save(country2);
            countryRepository.save(country3);

            City city1 = new City();
            city1.setCountry(country1);
            city1.setName("Barcelona");
            cityRepository.save(city1);

            City city2 = new City();
            city2.setCountry(country1);
            city2.setName("Madrid");
            cityRepository.save(city2);

            City city3 = new City();
            city3.setCountry(country1);
            city3.setName("Sevilla");
            cityRepository.save(city3);

            City city4 = new City();
            city4.setCountry(country2);
            city4.setName("Athens");
            cityRepository.save(city4);

            City city5 = new City();
            city5.setCountry(country3);
            city5.setName("Brussels");
            cityRepository.save(city5);

            City city6 = new City();
            city6.setCountry(country3);
            city6.setName("Antwerp");
            cityRepository.save(city6);


            Hotel hotel1 = new Hotel();
            hotel1.setName("Marriot Central");
            hotel1.setCity(city1);
            hotelRepository.save(hotel1);

            Hotel hotel2 = new Hotel();
            hotel2.setName("NH Hotels");
            hotel2.setCity(city1);
            hotelRepository.save(hotel2);

            Hotel hotel3 = new Hotel();
            hotel3.setName("Guillermo Tell Hotel");
            hotel3.setCity(city1);
            hotelRepository.save(hotel3);

            Hotel hotel4 = new Hotel();
            hotel4.setName("Marriot Central");
            hotel4.setCity(city1);
            hotelRepository.save(hotel4);

            Hotel hotel5 = new Hotel();
            hotel5.setName("Olimpiakos");
            hotel5.setCity(city3);
            hotelRepository.save(hotel5);

            Hotel hotel6 = new Hotel();
            hotel6.setName("Papavasiliou");
            hotel6.setCity(city3);
            hotelRepository.save(hotel6);


            Room room1 = new Room();
            room1.setRoomNumber(1);
            room1.setHotel(hotel1);
            roomRepository.save(room1);

            Room room2= new Room();
            room2.setRoomNumber(2);
            room2.setHotel(hotel1);
            roomRepository.save(room2);

            Room room3 = new Room();
            room3.setRoomNumber(3);
            room3.setHotel(hotel1);
            roomRepository.save(room3);

            Room room4 = new Room();
            room4.setRoomNumber(1);
            room4.setHotel(hotel2);
            roomRepository.save(room4);

            Room room5 = new Room();
            room5.setRoomNumber(2);
            room5.setHotel(hotel2);
            roomRepository.save(room5);

            Room room6 = new Room();
            room6.setRoomNumber(3);
            room6.setHotel(hotel2);
            roomRepository.save(room6);

            Room room7 = new Room();
            room7.setRoomNumber(1);
            room7.setHotel(hotel3);
            roomRepository.save(room7);

            Room room8 = new Room();
            room8.setRoomNumber(2);
            room8.setHotel(hotel3);
            roomRepository.save(room8);

            Room room9 = new Room();
            room9.setRoomNumber(3);
            room9.setHotel(hotel3);
            roomRepository.save(room9);

//            countryRepository.delete(country3);
        } catch (Exception e){
            System.out.println("exceptie sql");
        }


    }







}
