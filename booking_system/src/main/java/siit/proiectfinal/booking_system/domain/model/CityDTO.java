package siit.proiectfinal.booking_system.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import siit.proiectfinal.booking_system.domain.entity.Country;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private Integer id;
    private String name;

//    @JsonManagedReference
    private Country country;

//    @JsonIgnore
//    private List<HotelDTO> hotels;
}
