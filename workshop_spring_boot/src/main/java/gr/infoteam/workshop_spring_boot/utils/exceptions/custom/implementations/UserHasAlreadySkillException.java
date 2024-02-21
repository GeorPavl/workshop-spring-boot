package gr.infoteam.workshop_spring_boot.utils.exceptions.custom.implementations;

public class UserHasAlreadySkillException extends RuntimeException {

    public UserHasAlreadySkillException(String email, String skillName) {
        super(String.format("User %s has already skill %s", email, skillName));
    }
}
