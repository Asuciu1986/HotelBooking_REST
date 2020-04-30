package siit.proiectfinal.booking_system.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

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
