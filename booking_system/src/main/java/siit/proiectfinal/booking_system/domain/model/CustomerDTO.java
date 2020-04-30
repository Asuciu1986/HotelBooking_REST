package siit.proiectfinal.booking_system.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CustomerDTO{

    private Integer id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String password;

    @JsonIgnore
    private final LocalDateTime joiningDate = LocalDateTime.now();

    @JsonIgnore
    private final LocalDateTime editingDate = LocalDateTime.now();

    private LocalDate birthDate;
    private String countryOfResidence;
    private String email;
    private String phoneNumber;

    @JsonIgnore
    private List<ReservationDTO> reservations = new ArrayList<>();
}
