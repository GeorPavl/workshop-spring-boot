package gr.infoteam.workshop_spring_boot.features.user.dtos;

import gr.infoteam.workshop_spring_boot.features.user.validators.StrongPasswordValidator;
import jakarta.validation.constraints.Email;

public record ChangePasswordRequestDto(
        @Email
        String email,
        @StrongPasswordValidator
        String currentPassword,
        @StrongPasswordValidator
        String newPassword,
        @StrongPasswordValidator
        String confirmNewPassword
) {
}
