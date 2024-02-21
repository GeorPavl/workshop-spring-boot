package gr.infoteam.workshop_spring_boot.features.skill.services;

import gr.infoteam.workshop_spring_boot.features.skill.repositories.SkillRepository;
import gr.infoteam.workshop_spring_boot.utils.exceptions.custom.implementations.SkillNameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillUtilService {

    private final SkillRepository skillRepository;

    public void checkIfSkillNameAlreadyExists(String skillName) {
        var entity = skillRepository.findByName(skillName);

        if (entity.isPresent()) {
            throw new SkillNameAlreadyExistsException(skillName);
        }
    }
}
