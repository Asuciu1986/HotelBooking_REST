package siit.proiectfinal.booking_system.service;

import org.springframework.stereotype.Service;
import siit.proiectfinal.booking_system.domain.entity.Customer;
import siit.proiectfinal.booking_system.exception.CustomerNotFoundException;
import siit.proiectfinal.booking_system.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    //todo retrieve reservations of customer

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteCustomerById(int id){
        customerRepository.deleteById(id);
    }

    public Customer getCustomerById(int id){
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("CustomerService error"));
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
}
