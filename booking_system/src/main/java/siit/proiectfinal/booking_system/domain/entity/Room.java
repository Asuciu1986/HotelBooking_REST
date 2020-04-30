package siit.proiectfinal.booking_system.domain.entity;

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
@EqualsAndHashCode
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
    @Column
    private Integer roomNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;

    @Column
    private Integer size;

    @Column
    @Max(10)
    private Integer personCapacity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room", orphanRemoval = true)
    private List<RoomAvailability> availabilities;

}
