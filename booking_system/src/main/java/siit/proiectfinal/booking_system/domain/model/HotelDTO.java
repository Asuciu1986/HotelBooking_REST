package siit.proiectfinal.booking_system.domain.model;

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
    private CityDTO city;
    private Set<RoomDTO> rooms;
    private Integer stars;
    private String email;
    private String phoneNumber;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;

}
