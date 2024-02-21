package gr.infoteam.workshop_spring_boot.features.skill.mappers;

import gr.infoteam.workshop_spring_boot.features.skill.Skill;
import gr.infoteam.workshop_spring_boot.features.skill.dtos.SkillRequestDto;
import org.springframework.stereotype.Service;

@Service
public class SkillMapper {

    public Skill mapDtoToEntity(SkillRequestDto requestDto) {
        return Skill.builder()
                .name(requestDto.name())
                .build();
    }
}
