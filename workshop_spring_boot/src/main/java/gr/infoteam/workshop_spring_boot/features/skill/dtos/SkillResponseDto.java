package gr.infoteam.workshop_spring_boot.features.skill.dtos;

import gr.infoteam.workshop_spring_boot.features.skill.Skill;

import java.util.UUID;

public record SkillResponseDto(
        UUID id,
        String name
) {
    public SkillResponseDto(Skill skill) {
        this(skill.getId(), skill.getName());
    }
}
