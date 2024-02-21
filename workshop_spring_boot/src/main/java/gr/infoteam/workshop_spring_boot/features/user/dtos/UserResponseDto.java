package gr.infoteam.workshop_spring_boot.features.user.dtos;

import gr.infoteam.workshop_spring_boot.features.skill.Skill;
import gr.infoteam.workshop_spring_boot.features.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String password,
        String role,
        String phoneNumber,
        Boolean isAvailable,
        String location,
        String jobRole,
        List<String> skills
) {
    public UserResponseDto(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPassword(), user.getRole().name(), user.getUserInfo().getPhoneNumber(),
                user.getUserInfo().getIsAvailable(), user.getUserInfo().getLocation().name(),
                user.getUserInfo().getJobRole().name(), convertSkillsToStringList(user.getSkills()));
    }

    private static List<String> convertSkillsToStringList(List<Skill> skills) {
        if (skills == null || skills.isEmpty()) {
            return new ArrayList<>();
        }

        return skills.stream()
                .map(Skill::getName)
                .toList();
    }
}
