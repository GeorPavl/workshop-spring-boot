package gr.infoteam.workshop_spring_boot.user.dtos;

import jakarta.validation.constraints.NotEmpty;

public record UserRequestDto(
        @NotEmpty(message = "First name should not be empty")
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
}
