package siit.proiectfinal.booking_system.domain.model;

import lombok.*;
import siit.proiectfinal.booking_system.domain.entity.ReservationStatus;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ReservationDTO {

    private Integer id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private CustomerDTO customer;
    private RoomAvailabilityDTO room;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
}