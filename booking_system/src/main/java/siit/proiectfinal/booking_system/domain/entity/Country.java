package siit.proiectfinal.booking_system.domain.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Size(max = 50)
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<City> cities = new HashSet<>();

}
