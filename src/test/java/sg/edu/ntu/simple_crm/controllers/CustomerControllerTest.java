package sg.edu.ntu.simple_crm.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import sg.edu.ntu.simple_crm.data.Customer;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectmapper;

    @DisplayName("Get customer by Id")
    @Test
    public void getCustomerByIdTest() throws Exception {
        // Step 1: Build GET request to /customers/1
        RequestBuilder request = MockMvcRequestBuilders.get("/customers/1");

        // Step 2, 3: Execute the request and assert the response
        mockMvc.perform(request)
            // Assert that the response status code is 200
            .andExpect(status().isOk())
            // Assert that the response content-type is application/JSON
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            // Assert that the result returned is id = 1;
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.firstName").value("Bruce"));
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        // Step 1: Build GET request to /customers
        RequestBuilder request = MockMvcRequestBuilders.get("/customers");

        // Step 2, 3: Execute the request and assert the response
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.size()").value(5));
    }
    
    @Test
    public void validCustomerCreationTest() throws Exception {
        // Step 1: Create customer object
        Customer newCustomer = Customer.builder().firstName("Clint").lastName("Barton").email("clint@avengers.com")
        .contactNo("12345678").jobTitle("Special Agent").yearOfBirth(1975).build();

        // Step 2: Convert Java object to JSON using ObjectMapper
        String newCustomerAsJson = objectmapper.writeValueAsString(newCustomer);

        // Step 3: Build the POST request
        RequestBuilder request = MockMvcRequestBuilders.post("/customers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(newCustomerAsJson);
        
        // Step 4: Execute and Assert
        mockMvc.perform(request)
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(6))
            .andExpect(jsonPath("$.firstName").value("Clint"))
            .andExpect(jsonPath("$.lastName").value("Barton"));
    }

    @Test
    public void invalidCustomerCreationTest() throws Exception {
        Customer invalidCustomer = new Customer(3L, "", "", "bruce.com", "12345678", "Manager", 1990, null);
        
        String invalidCustomerAsJson = objectmapper.writeValueAsString(invalidCustomer);

        RequestBuilder request = MockMvcRequestBuilders.post("/customers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidCustomerAsJson);

        mockMvc.perform(request)
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
}
