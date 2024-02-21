package gr.infoteam.workshop_spring_boot.utils.exceptions.custom.implementations;

public class ConfirmPasswordNotMatchException extends RuntimeException {

    public ConfirmPasswordNotMatchException(String message) {
        super(message);
    }
}
