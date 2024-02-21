package gr.infoteam.workshop_spring_boot.features.skill.services;

import gr.infoteam.workshop_spring_boot.features.skill.Skill;
import gr.infoteam.workshop_spring_boot.features.skill.dtos.SkillRequestDto;
import gr.infoteam.workshop_spring_boot.features.skill.dtos.SkillResponseDto;
import gr.infoteam.workshop_spring_boot.features.skill.mappers.SkillMapper;
import gr.infoteam.workshop_spring_boot.features.skill.repositories.SkillRepository;
import gr.infoteam.workshop_spring_boot.utils.exceptions.custom.implementations.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final SkillUtilService skillUtilService;
    private final SkillMapper skillMapper;

    @Override
    public List<SkillResponseDto> getAll() {
        return skillRepository.findAll()
                .stream()
                .map(SkillResponseDto::new)
                .toList();
    }

    @Override
    public Skill getEntityByName(String name) {
        return skillRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(Skill.class.getSimpleName(),
                        "name", name));
    }

    @Override
    public SkillResponseDto create(SkillRequestDto requestDto) {
        skillUtilService.checkIfSkillNameAlreadyExists(requestDto.name());
        var mappedDto = skillMapper.mapDtoToEntity(requestDto);
        var savedSkill = skillRepository.save(mappedDto);
        return new SkillResponseDto(savedSkill);
    }
}
