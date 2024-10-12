package sg.edu.ntu.simple_crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.simple_crm.data.Customer;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    // Custom query to find all customers with a certain first name
    List<Customer> findByFirstName(String firstName);
}
