package siit.proiectfinal.booking_system.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import siit.proiectfinal.booking_system.domain.entity.RoomAvailabilityStatus;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomAvailabilityDTO{

    private Integer id;
    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    private RoomAvailabilityStatus roomAvailabilityStatus;

    private BigDecimal rentPricePerNight;

    @JsonBackReference
    private RoomDTO room;

}
