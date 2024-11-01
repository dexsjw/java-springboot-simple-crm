package sg.edu.ntu.simple_crm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sg.edu.ntu.simple_crm.data.Customer;
import sg.edu.ntu.simple_crm.data.Interaction;
import sg.edu.ntu.simple_crm.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    // public CustomerController(@Qualifier("customerServiceWithLoggingImpl") CustomerService customerService) {
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/dummy")
    public ResponseEntity<String> dummyEndpoint() {
        return ResponseEntity.ok().body("This is a dummy endpoint to test cicd");
    }

    // Create /customers, HttpStatusCode: 201
    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    // Read - Get all /customers, HttpStatusCode: 200
    @GetMapping({"", "/"})
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    // Read - (Get one) /customers/{id}, HttpStatusCode: 200
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
    // public ResponseEntity<Customer> getCustomer(@PathVariable String id) {
        // try {
        //     return ResponseEntity.ok().body(customerService.getCustomer(id));
        // } catch (CustomerNotFoundException cnfe) {
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        // }
        return ResponseEntity.ok().body(customerService.getCustomer(id));
    }

    // Update /customers/{id}, HttpStatusCode: 200
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customer) {
    // public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        // try {
        //     return ResponseEntity.ok().body(customerService.updateCustomer(id, customer));
        // } catch (CustomerNotFoundException cnfe) {
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        // }
        return ResponseEntity.ok().body(customerService.updateCustomer(id, customer));
    }

    // Delete /customers/{id}, HttpStatusCode: 204
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {
    // public ResponseEntity<Customer> deleteCustomer(@PathVariable String id) {
        // try {
        //     customerService.deleteCustomer(id);
        //     return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        // } catch (CustomerNotFoundException cnfe) {
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        // }
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // Nested routes
    @PostMapping("/{id}/interactions")
    public ResponseEntity<Interaction> addInteractionToCustomer(@PathVariable Long id, @Valid @RequestBody Interaction interaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addInteractionToCustomer(id, interaction));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomers(@RequestParam String firstName) {
        return ResponseEntity.ok(customerService.searchCustomers(firstName));
    }
}
