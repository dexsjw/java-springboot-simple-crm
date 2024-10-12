package sg.edu.ntu.simple_crm.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import sg.edu.ntu.simple_crm.data.ErrorResponse;
import sg.edu.ntu.simple_crm.exceptions.CustomerNotFoundException;
import sg.edu.ntu.simple_crm.exceptions.InteractionNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler(CustomerNotFoundException.class)
    // public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException cnfe) {
    //     ErrorResponse errorResponse = new ErrorResponse(cnfe.getMessage(), LocalDateTime.now());
    //     return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    // }

    // @ExceptionHandler(InteractionNotFoundException.class)
    // public ResponseEntity<ErrorResponse> handleInteractionNotFoundException(InteractionNotFoundException infe) {
    //     ErrorResponse errorResponse = new ErrorResponse(infe.getMessage(), LocalDateTime.now());
    //     return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    // }

    // Handling Exceptions of same nature i.e. similar implementation
    @ExceptionHandler({CustomerNotFoundException.class, InteractionNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // MethodArgumentNotValidException -> thrown when there was no handling of validation
    // i.e. In CustomerController, when @Valid is used but no BindingResult passed in as argument
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // get a list of all validation errors from the exception object
        List<ObjectError> validationErrors = ex.getBindingResult().getAllErrors();

        // Create stringbuilder to store all the error messages
        StringBuilder sb = new StringBuilder();

        for (ObjectError error : validationErrors) {
            sb.append(error.getDefaultMessage() + ". ");
        }

        ErrorResponse errorResponse = new ErrorResponse(sb.toString(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        // logger.error(ex.getMessage(), ex); -> to log out error for debugging later
        ErrorResponse errorResponse = new ErrorResponse("Something went wrong", LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
