package gr.infoteam.workshop_spring_boot.features.skill.services;

import gr.infoteam.workshop_spring_boot.features.skill.Skill;
import gr.infoteam.workshop_spring_boot.features.skill.repositories.SkillRepository;
import gr.infoteam.workshop_spring_boot.features.user.User;
import gr.infoteam.workshop_spring_boot.utils.exceptions.custom.implementations.SkillNameAlreadyExistsException;
import gr.infoteam.workshop_spring_boot.utils.exceptions.custom.implementations.UserHasAlreadySkillException;
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

    public void checkIfUserHasAlreadyThisSkill(User user, Skill skill) {
        if (user.getSkills().contains(skill)) {
            throw new UserHasAlreadySkillException(user.getEmail(), skill.getName());
        }
    }
}
