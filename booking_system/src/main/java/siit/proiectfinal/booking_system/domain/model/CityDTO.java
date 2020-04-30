package siit.proiectfinal.booking_system.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import siit.proiectfinal.booking_system.domain.entity.Country;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CityDTO {

    private Integer id;
    private String name;

    @JsonBackReference
    private Country country;

    @JsonManagedReference
    private List<HotelDTO> hotels;
}
