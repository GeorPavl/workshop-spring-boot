package gr.infoteam.workshop_spring_boot.features.skill.services;

import gr.infoteam.workshop_spring_boot.features.skill.Skill;
import gr.infoteam.workshop_spring_boot.features.skill.dtos.SkillRequestDto;
import gr.infoteam.workshop_spring_boot.features.skill.dtos.SkillResponseDto;

import java.util.List;

public interface SkillService {
    List<SkillResponseDto> getAll();
    Skill getEntityByName(String name);
    SkillResponseDto create(SkillRequestDto requestDto);
}
