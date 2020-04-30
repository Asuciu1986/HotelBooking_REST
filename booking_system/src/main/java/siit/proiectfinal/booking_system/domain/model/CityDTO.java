package siit.proiectfinal.booking_system.domain.model;

import lombok.*;
import siit.proiectfinal.booking_system.domain.entity.Country;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CityDTO {

    private Integer id;
    private String name;
    private Country country;
}
