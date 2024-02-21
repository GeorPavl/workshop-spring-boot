package gr.infoteam.workshop_spring_boot.user.dtos;

import gr.infoteam.workshop_spring_boot.user.User;

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
        String jobRole
) {
    public UserResponseDto(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPassword(), user.getRole().name(), user.getUserInfo().getPhoneNumber(),
                user.getUserInfo().getIsAvailable(), user.getUserInfo().getLocation().name(),
                user.getUserInfo().getJobRole().name());
    }
}
