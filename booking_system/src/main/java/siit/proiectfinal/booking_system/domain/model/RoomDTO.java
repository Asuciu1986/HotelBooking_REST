package siit.proiectfinal.booking_system.domain.model;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoomDTO {

    private Integer id;
    private Integer roomNumber;
    private HotelDTO hotel;
    private Integer size;
    private Integer personCapacity;
    private List<RoomAvailabilityDTO> availabilities = new ArrayList<>();

}
