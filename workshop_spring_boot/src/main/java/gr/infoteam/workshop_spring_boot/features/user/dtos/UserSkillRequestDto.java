package gr.infoteam.workshop_spring_boot.features.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserSkillRequestDto(
        @Email(message = "{validations.user.email}")
        String email,
        @NotEmpty(message = "{validations.skill.notEmpty}")
        String skill
) {
}
