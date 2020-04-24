package siit.proiectfinal.booking_system.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Reservation extends Base{

    @Column
    private LocalDate checkInDate;

    @Column
    private LocalDate checkOutDate;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private RoomAvailability room;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
}
