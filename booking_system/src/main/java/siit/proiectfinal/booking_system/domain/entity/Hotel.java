package siit.proiectfinal.booking_system.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Size(max=100)
    @NotBlank
    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonIgnore
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
