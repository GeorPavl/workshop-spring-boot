package gr.infoteam.workshop_spring_boot.features.skill.repositories;

import gr.infoteam.workshop_spring_boot.features.skill.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, UUID> {
}
