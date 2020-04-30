package siit.proiectfinal.booking_system.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoomAvailability{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RoomAvailabilityStatus roomAvailabilityStatus;

    @NotNull
    @Column
    private BigDecimal rentPricePerNight;

    @ManyToOne
    private Room room;

}
