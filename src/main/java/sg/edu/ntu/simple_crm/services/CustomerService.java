package sg.edu.ntu.simple_crm.services;

import java.util.List;

import sg.edu.ntu.simple_crm.data.Customer;
import sg.edu.ntu.simple_crm.data.Interaction;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    // Customer getCustomer(String id);
    Customer getCustomer(Long id);

    List<Customer> getAllCustomers();

    // Customer updateCustomer(String id, Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    
    // void deleteCustomer(String id);
    void deleteCustomer(Long id);

    Interaction addInteractionToCustomer(Long id, Interaction interaction);

    List<Customer> searchCustomers(String firstName);
}
