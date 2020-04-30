package siit.proiectfinal.booking_system.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HotelDTO {

    private Integer id;
    private String name;

    @JsonBackReference
    private CityDTO city;

    @JsonManagedReference
    private Set<RoomDTO> rooms;

    private Integer stars;
    private String email;
    private String phoneNumber;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;

}
