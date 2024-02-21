package gr.infoteam.workshop_spring_boot.utils.exceptions.custom.handlers;

import gr.infoteam.workshop_spring_boot.utils.exceptions.ErrorDetails;
import gr.infoteam.workshop_spring_boot.utils.exceptions.ErrorResponse;
import gr.infoteam.workshop_spring_boot.utils.exceptions.custom.implementations.ConfirmPasswordNotMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class ConfirmPasswordNotMatchExceptionHandler {

    @ExceptionHandler({ConfirmPasswordNotMatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConfirmPasswordNotMatchException(
            ConfirmPasswordNotMatchException exception) {
        var errorResponse = new ErrorResponse(
                Arrays.asList(
                        new ErrorDetails(
                                LocalDateTime.now(),
                                exception.getMessage(),
                                HttpStatus.BAD_REQUEST
                        )
                )
        );


        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }
}
