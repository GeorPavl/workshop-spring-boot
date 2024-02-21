package gr.infoteam.workshop_spring_boot.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorReponse> handleAllExceptions (Exception exception) {
        var errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                HttpStatus.CONFLICT);

        return ResponseEntity
                .internalServerError()
                .body(new ErrorReponse(Arrays.asList(errorDetails)));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorReponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        var errors = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .toList();

        var errorDetailsList = errors
                .stream()
                .map(error -> new ErrorDetails(
                        LocalDateTime.now(),
                        error.getDefaultMessage(),
                        HttpStatus.UNPROCESSABLE_ENTITY))
                .toList();

        return ResponseEntity
                .unprocessableEntity()
                .body(new ErrorReponse(errorDetailsList));
    }
}
