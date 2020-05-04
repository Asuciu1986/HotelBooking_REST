package siit.proiectfinal.booking_system.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
//
//    @JsonManagedReference
    private Integer cityId;

//    @JsonIgnore
//    private Set<RoomDTO> rooms;

    private Integer stars;
    private String email;
    private String phoneNumber;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;

}
