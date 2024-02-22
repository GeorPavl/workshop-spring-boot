package gr.infoteam.workshop_spring_boot.utils.exceptions;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorResponse {

    private List<ErrorDetails> errors;
}
