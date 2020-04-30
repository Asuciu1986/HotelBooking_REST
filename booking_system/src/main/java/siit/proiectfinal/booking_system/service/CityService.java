package siit.proiectfinal.booking_system.service;

import org.springframework.stereotype.Service;
import siit.proiectfinal.booking_system.domain.entity.City;
import siit.proiectfinal.booking_system.repository.CityRepository;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City createCity(City city){
        return cityRepository.save(city);
    }

    public City updateCity(City city){
        return cityRepository.save(city);
    }

    public void deleteCityById(int id){
        cityRepository.deleteById(id);
    }

    public City getCityById(int id){
        return cityRepository.findById(id).orElse(null);
    }

    public List<City> getCountries(){
        return cityRepository.findAll();
    }



}
