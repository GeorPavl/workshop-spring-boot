package gr.infoteam.workshop_spring_boot.features.user.dtos;

import gr.infoteam.workshop_spring_boot.features.user.validators.StrongPasswordValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotEmpty(message = "{validations.user.firstName.notEmpty}")
        @Size(min = 2, max = 50, message = "{validations.user.firstName.size}")
        String firstName,
        @NotEmpty(message = "{validations.user.lastName.notEmpty}")
        @Size(min = 2, max = 50, message = "{validations.user.lastName.size}")
        String lastName,
        @Email(message = "{validations.user.email}")
        String email,
        @StrongPasswordValidator
        String password,
        String role,
        String phoneNumber,
        Boolean isAvailable,
        String location,
        String jobRole
) {
}
