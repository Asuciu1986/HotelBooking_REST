package siit.proiectfinal.booking_system.service;

import org.springframework.stereotype.Service;
import siit.proiectfinal.booking_system.domain.entity.Country;
import siit.proiectfinal.booking_system.repository.CountryRepository;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country createCountry(Country country){
        return countryRepository.save(country);
    }

    public Country updateCountry(Country country){
        return countryRepository.save(country);
    }

    public void deleteCountryById(int id){
        countryRepository.deleteById(id);
    }

    public Country getCountryById(int id){
        return countryRepository.findById(id).orElse(null);
    }

    public List<Country> getCountries(){
        return countryRepository.findAll();
    }


}
