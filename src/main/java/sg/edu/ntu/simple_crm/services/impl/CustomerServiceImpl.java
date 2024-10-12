package sg.edu.ntu.simple_crm.services.impl;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import sg.edu.ntu.simple_crm.data.Customer;
import sg.edu.ntu.simple_crm.data.Interaction;
import sg.edu.ntu.simple_crm.exceptions.CustomerNotFoundException;
import sg.edu.ntu.simple_crm.repositories.CustomerRepository;
import sg.edu.ntu.simple_crm.repositories.InteractionRepository;
import sg.edu.ntu.simple_crm.services.CustomerService;

@Primary
@Service
// With reference to CustomerServiceWithLoggingImpl
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private InteractionRepository interactionRepository;

    // @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, InteractionRepository interactionRepository) {
        this.customerRepository = customerRepository;
        this.interactionRepository = interactionRepository;
    }

    // CREATE
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);    }

    // READ - Get one
    public Customer getCustomer(Long id) {
        // return customerRepository.findById(id).get();
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    // READ - Get all
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // UPDATE
    public Customer updateCustomer(Long id, Customer customer) {
        // Retrieve customer from database
        // Customer customerToUpdate = customerRepository.findById(id).get();
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        
        // update the customer retrieved from the database
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setContactNo(customer.getContactNo());
        customerToUpdate.setJobTitle(customer.getJobTitle());
        customerToUpdate.setYearOfBirth(customer.getYearOfBirth());
        
        // save the updated customer back to the database
        return customerRepository.save(customerToUpdate);    }

    // DELETE
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Interaction addInteractionToCustomer(Long id, Interaction interaction) {
        // retrieve the customer from the database
        // Customer selectedCustomer = customerRepository.findById(id).get();
        Customer selectedCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        // add the customer to the interaction
        interaction.setCustomer(selectedCustomer);
        // save the interaction to the database
        return interactionRepository.save(interaction);
    }

    @Override
    public List<Customer> searchCustomers(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    // private int getCustomerIndex(String id) {
    //     for (Customer customer : customerRepository.getAllCustomers()) {
    //         if (customer.getId().equals(id)) {
    //             return customerRepository.getAllCustomers().indexOf(customer);
    //         }
    //     }
    //     // Not found
    //     throw new CustomerNotFoundException(id);
    // }
}
