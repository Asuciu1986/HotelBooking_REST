package siit.proiectfinal.booking_system.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Hotel extends Base{

    @Size(max=100)
    @NotBlank
    @Column
    private String name;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<Room> rooms;

    @Column
    private Integer stars;

    @Size(max=50)
    @Column
    private String email;

    @Size(max=13)
    @Column
    private String phoneNumber;

    @Column
    private LocalTime checkInTime;

    @Column
    private LocalTime checkOutTime;

}
