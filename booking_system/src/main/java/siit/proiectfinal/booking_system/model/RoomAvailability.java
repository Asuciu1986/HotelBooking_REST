package siit.proiectfinal.booking_system.model;

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
@EqualsAndHashCode(callSuper = true)
public class RoomAvailability extends Base  {

    @Column
    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    private RoomAvailabilityStatus roomAvailabilityStatus;

    @NotNull
    @Column
    private BigDecimal rentPricePerNight;

    @ManyToOne
    private Room room;



}
