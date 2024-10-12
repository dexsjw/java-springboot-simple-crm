package sg.edu.ntu.simple_crm.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.ntu.simple_crm.data.Customer;
import sg.edu.ntu.simple_crm.exceptions.CustomerNotFoundException;
import sg.edu.ntu.simple_crm.repositories.CustomerRepository;
import sg.edu.ntu.simple_crm.services.impl.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceImplTest {

    // Mock the customer repository
    @Mock
    private CustomerRepository customerRepository;

    // Inject the mocked customer repository into the customer service
    @InjectMocks
    CustomerServiceImpl customerServiceImpl;

    @Test
    public void createCustomerTest() {

        // 1.SETUP
        // Create a customer
        Customer customer = Customer.builder().firstName("Clint").lastName("Barton").email("clint@avengers.com")
        .contactNo("12345678").jobTitle("Special Agent").yearOfBirth(1975).build();

        // Mock the save method behaviour
        // Note that we are assuming that customerRepository is working as expected
        when(customerRepository.save(customer)).thenReturn(customer);

        // 2.EXECUTE
        // Call the createCustomer method
        Customer savedCustomer = customerServiceImpl.createCustomer(customer);

        // 3.ASSERT
        // Compare actual with expected
        assertEquals(customer, savedCustomer, "The saved customer should be the same as the new customer");

        // Also verify that the save method is called once only
        verify(customerRepository, times(1)).save(customer);
    }
    
    @Test
    public void getCustomerTest() {
        // 1.SETUP
        // Create the customer to get
        Customer customer = Customer.builder().firstName("Clint").lastName("Barton").email("clint@avengers.com")
        .contactNo("12345678").jobTitle("Special Agent").yearOfBirth(1975).build();

        Long customerId = 1L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // 2.EXECUTE
        Customer retrievedCustomer = customerServiceImpl.getCustomer(customerId);

        // 3.ASSERT
        assertEquals(customer, retrievedCustomer);
    }

    @Test
    public void getCustomerNotFoundTest() {
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerServiceImpl.getCustomer(customerId));
    }
}
