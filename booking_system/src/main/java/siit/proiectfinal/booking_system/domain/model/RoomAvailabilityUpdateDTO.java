package siit.proiectfinal.booking_system.domain.model;

import lombok.Getter;
import lombok.Setter;
import siit.proiectfinal.booking_system.domain.entity.RoomAvailabilityStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Getter
@Setter
public class RoomAvailabilityUpdateDTO {

    private Integer id;

    private BigDecimal rentPricePerNight;

    @Enumerated(EnumType.STRING)
    private RoomAvailabilityStatus roomAvailabilityStatus;
}
