package siit.proiectfinal.booking_system.controller;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import siit.proiectfinal.booking_system.domain.entity.Customer;
import siit.proiectfinal.booking_system.domain.model.CustomerDTO;
import siit.proiectfinal.booking_system.domain.model.CustomerUpdateDTO;
import siit.proiectfinal.booking_system.exception.CustomerNotFoundException;
import siit.proiectfinal.booking_system.service.CustomerService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    ModelMapper modelMapper;

    //todo add cutomer/id/reservation


    @GetMapping
    public List<CustomerDTO> getCustomers(){
        return customerService.getCustomers().stream()
                .map(x -> modelMapper.map(x,CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomer(@PathVariable(name = "id") int customerId, HttpServletResponse response) {
        try {
            return modelMapper.map(customerService.getCustomerById(customerId), CustomerDTO.class);
        } catch (CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer createdCustomer = customerService.createCustomer(customer);
        return modelMapper.map(createdCustomer, CustomerDTO.class);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO updateCustomer(@PathVariable int id, @RequestBody CustomerUpdateDTO customerUpdateDTO) {

        customerUpdateDTO.setId(id);
        try {
            Customer selectedCustomer = customerService.getCustomerById(id);
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(customerUpdateDTO, selectedCustomer);
            return modelMapper.map(customerService.updateCustomer(selectedCustomer), CustomerDTO.class);
        } catch (CustomerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO){

        customerDTO.setId(id);
        Customer createdCustomer = customerService.updateCustomer(modelMapper.map(customerDTO,Customer.class));
        return modelMapper.map(createdCustomer,CustomerDTO.class);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCar(@PathVariable(name="id") int customerId){
        customerService.deleteCustomerById(customerId);
    }



//    @ExceptionHandler(CustomerNotFoundException.class)
//    public void notFound(HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.NOT_FOUND.value());
//    }
}
