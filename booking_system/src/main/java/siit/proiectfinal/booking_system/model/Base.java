package siit.proiectfinal.booking_system.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

}
