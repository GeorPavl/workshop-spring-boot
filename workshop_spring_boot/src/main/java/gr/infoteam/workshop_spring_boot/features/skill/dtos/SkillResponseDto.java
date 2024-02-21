package gr.infoteam.workshop_spring_boot.features.skill.dtos;

import gr.infoteam.workshop_spring_boot.features.skill.Skill;
import gr.infoteam.workshop_spring_boot.features.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record SkillResponseDto(
        UUID id,
        String name,
        List<String> users
) {
    public SkillResponseDto(Skill skill) {
        this(skill.getId(), skill.getName(), convertUsersToStringList(skill.getUsers()));
    }

    private static List<String> convertUsersToStringList(List<User> users) {
        if (users == null || users.isEmpty()) {
            return new ArrayList<>();
        }

        return users.stream()
                .map(User::getEmail)
                .toList();
    }
}
