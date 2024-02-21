package gr.infoteam.workshop_spring_boot.utils.config.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorDetails(
        LocalDateTime timestamp,
        String message,
        HttpStatus httpStatus
) {
}
