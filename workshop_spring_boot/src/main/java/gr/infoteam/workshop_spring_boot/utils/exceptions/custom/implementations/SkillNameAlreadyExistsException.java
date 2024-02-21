package gr.infoteam.workshop_spring_boot.utils.exceptions.custom.implementations;

public class SkillNameAlreadyExistsException extends RuntimeException {
    private String skillName;

    public SkillNameAlreadyExistsException(String skillName) {
        super(String.format("Skill %s already exists", skillName));
        this.skillName = skillName;
    }
}
