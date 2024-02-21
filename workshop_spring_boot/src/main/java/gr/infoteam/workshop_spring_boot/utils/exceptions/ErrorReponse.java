package gr.infoteam.workshop_spring_boot.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorReponse {

    private List<ErrorDetails> errors;
}
