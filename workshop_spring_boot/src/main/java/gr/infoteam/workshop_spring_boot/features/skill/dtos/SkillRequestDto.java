package gr.infoteam.workshop_spring_boot.features.skill.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record SkillRequestDto(
        @NotEmpty(message = "{validations.skill.notEmpty}")
        @Size(max = 50, message = "{validations.skill.size}")
        String name
) {
}
