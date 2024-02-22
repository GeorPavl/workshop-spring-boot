package gr.infoteam.workshop_spring_boot.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleOtherException(Exception exception) {
        var errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponse(Arrays.asList(errorDetails)));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
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
                .body(new ErrorResponse(errorDetailsList));
    }
}
