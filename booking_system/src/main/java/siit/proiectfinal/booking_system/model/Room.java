package siit.proiectfinal.booking_system.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Room extends Base {

    @NotNull
    @Column
    private Integer roomNumber;

    @ManyToOne
    private Hotel hotel;

    @Column
    private Integer size;

    @Column
    @Max(10)
    private Integer personCapacity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room", orphanRemoval = true)
    private List<RoomAvailability> availabilities = new ArrayList<>();

}
