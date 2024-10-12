package sg.edu.ntu.simple_crm.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    // CustomerNotFoundException(String id) {
    public CustomerNotFoundException(Long id) {
        super("Could not find customer with id " + id);
    }
}
