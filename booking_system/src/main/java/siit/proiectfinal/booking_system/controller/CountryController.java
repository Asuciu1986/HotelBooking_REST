package siit.proiectfinal.booking_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import siit.proiectfinal.booking_system.domain.mapper.CountryMapper;
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
    private final CountryMapper countryMapper;

    public CountryController(CountryService countryService, CountryMapper countryMapper) {
        this.countryService = countryService;
        this.countryMapper = countryMapper;
    }

    @GetMapping(value = "/{id}")
    public CountryDTO getCountry(@PathVariable(name = "id") int countryId) {
        return countryMapper.toDTO(countryService.getCountryById(countryId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CountryDTO> getCountries() {
        List<Country> countries = countryService.getCountries();
        return countries.stream().map(countryMapper::toDTO).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCountry(@PathVariable(name = "id") int countryId) {
        countryService.deleteCountryById(countryId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CountryDTO createCountry(@RequestBody @Valid CountryDTO countryDTO) throws ParseException {
        Country country = countryMapper.toEntity(countryDTO);
        Country countryCreated = countryService.createCountry(country);
        return countryMapper.toDTO(countryCreated);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CountryDTO updateCountry(@RequestBody @Valid CountryDTO countryDTO) throws ParseException {
        Country country = countryMapper.toEntity(countryDTO);
        return countryMapper.toDTO(countryService.updateCountry(country));
    }

    @ExceptionHandler(ValidationException.class)
    public void badRequest(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

}
