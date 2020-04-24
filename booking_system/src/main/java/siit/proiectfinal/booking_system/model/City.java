package siit.proiectfinal.booking_system.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class City extends Base {

    @Size(max=50)
    @Column
    private String name;

    @ManyToOne
    private Country country;
}
