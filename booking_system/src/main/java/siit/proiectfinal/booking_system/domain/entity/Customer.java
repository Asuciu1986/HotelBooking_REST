package siit.proiectfinal.booking_system.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String password;

    @Column
    private LocalDate birthDate;

    @Column
    private LocalDateTime joiningDate;

    @Column
    private LocalDateTime editingDate;

    @Column
    private String countryOfResidence;

    @Email
    @Column
    private String email;

    @Column
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservations = new ArrayList<>();
}
