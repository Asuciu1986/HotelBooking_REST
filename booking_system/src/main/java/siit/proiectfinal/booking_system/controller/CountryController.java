package siit.proiectfinal.booking_system.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import siit.proiectfinal.booking_system.domain.model.CountryDTO;
import siit.proiectfinal.booking_system.exception.CountryNotFoundException;
import siit.proiectfinal.booking_system.domain.entity.Country;
import siit.proiectfinal.booking_system.service.CountryService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    private ModelMapper modelMapper;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = "/{id}")
    public CountryDTO getCountry(@PathVariable(name = "id") int countryId) {
        return convertToDto(countryService.getCountryById(countryId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CountryDTO> getCountries() {
        List<Country> countries = countryService.getCountries();
        return countries.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCountry(@PathVariable(name = "id") int countryId) {
        countryService.deleteCountryById(countryId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CountryDTO createCountry(@RequestBody @Valid CountryDTO countryDTO) throws ParseException {
        Country country = convertToEntity(countryDTO);
        Country countryCreated = countryService.createCountry(country);
        return convertToDto(countryCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCountry(@RequestBody @Valid CountryDTO countryDTO) throws ParseException {
        Country country = convertToEntity(countryDTO);
        countryService.updateCountry(country);
    }

    @ExceptionHandler(ValidationException.class)
    public void badRequest(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    private CountryDTO convertToDto(Country country) {
        return modelMapper.map(country, CountryDTO.class);
    }

    private Country convertToEntity(CountryDTO countryDTO) throws ParseException {
        Country country = modelMapper.map(countryDTO, Country.class);
        return country;
    }
}
