package sg.edu.ntu.simple_crm.exceptions;

public class InteractionNotFoundException extends RuntimeException {
    public InteractionNotFoundException(Long id) {
        super("Unable to find interaction with id: " + id + ".");
    }
}
