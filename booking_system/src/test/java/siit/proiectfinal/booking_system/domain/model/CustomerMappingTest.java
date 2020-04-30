package siit.proiectfinal.booking_system.domain.model;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import siit.proiectfinal.booking_system.domain.entity.Customer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;


public class CustomerMappingTest {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkMapping(){

        CustomerDTO createdDTO = new CustomerDTO();
        createdDTO.setFirstName("Johnny");
        createdDTO.setLastName("Cash");
        createdDTO.setBirthDate(LocalDate.now().minusYears(20));
        createdDTO.setCountryOfResidence("USA");
        createdDTO.setPassword("alex1234");

        Customer customer = modelMapper.map(createdDTO,Customer.class);
        assertEquals(createdDTO.getFirstName(),customer.getFirstName());
        assertEquals(createdDTO.getLastName(),customer.getLastName());
        assertEquals(createdDTO.getJoiningDate(),customer.getJoiningDate());
        assertEquals(createdDTO.getEditingDate(),customer.getEditingDate());
        assertEquals(createdDTO.getBirthDate(),customer.getBirthDate());
        assertEquals(createdDTO.getCountryOfResidence(),customer.getCountryOfResidence());


    }

}
