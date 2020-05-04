package siit.proiectfinal.booking_system.domain.model;

import lombok.*;
import java.time.LocalDate;



@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CustomerUpdateDTO {

    private Integer id;

    private String lastName;

    private String password;

    private final LocalDate editingDate = LocalDate.now();

    private String countryOfResidence;
    private String email;
    private String phoneNumber;
    //private List<ReservationDTO> reservations = new ArrayList<>();
}
