package siit.proiectfinal.booking_system.domain.model;

import lombok.*;
import siit.proiectfinal.booking_system.domain.entity.RoomAvailabilityStatus;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoomAvailabilityDTO{


    private Integer id;
    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    private RoomAvailabilityStatus roomAvailabilityStatus;

    private BigDecimal rentPricePerNight;
    private RoomDTO room;

}
