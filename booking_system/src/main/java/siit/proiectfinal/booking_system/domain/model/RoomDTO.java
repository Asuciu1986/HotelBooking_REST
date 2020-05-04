package siit.proiectfinal.booking_system.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    private Integer id;
    private Integer roomNumber;

//    @JsonManagedReference
    private HotelDTO hotel;

    private Integer size;
    private Integer personCapacity;

//    @JsonIgnore
//    private List<RoomAvailabilityDTO> availabilities = new ArrayList<>();

}
