package gr.infoteam.workshop_spring_boot.features.user.services;

import gr.infoteam.workshop_spring_boot.features.user.User;
import gr.infoteam.workshop_spring_boot.features.user.dtos.ChangePasswordRequestDto;
import gr.infoteam.workshop_spring_boot.features.user.enums.Role;
import gr.infoteam.workshop_spring_boot.features.user.repositories.UserRepository;
import gr.infoteam.workshop_spring_boot.utils.exceptions.custom.implementations.ConfirmPasswordNotMatchException;
import gr.infoteam.workshop_spring_boot.utils.exceptions.custom.implementations.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Tag("Unit_Test")
@ExtendWith(MockitoExtension.class)
@DisplayName("User utils service unit test")
class UserUtilServiceTest {

    @InjectMocks
    private UserUtilService userUtilService;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldValidateCredentialsSuccessfullyAndNotThrowException() {
        var mockEmail = "mail@mail.com";
        var mockRequestDto = ChangePasswordRequestDto.builder()
                .email(mockEmail)
                .currentPassword("Password123!@#")
                .newPassword("Password234@#$")
                .confirmNewPassword("Password234@#$")
                .build();

        var mockUserToReturn = User.builder()
                        .firstName("First")
                        .lastName("Last")
                        .email(mockEmail)
                        .password("Password123!@#")
                        .role(Role.ADMIN)
                        .build();

        when(userRepository.findByEmail(mockEmail))
                .thenReturn(Optional.of(mockUserToReturn));

        assertDoesNotThrow(() -> userUtilService.validateCredentialsForChangePassword(mockRequestDto));
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenUserNotExists() {
        var mockEmail = "mail@mail.com";
        var mockRequestDto = ChangePasswordRequestDto.builder()
                .email(mockEmail)
                .currentPassword("Password123!@#")
                .newPassword("Password123@#$")
                .confirmNewPassword("Password123@#$")
                .build();

        when(userRepository.findByEmail(mockEmail))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> userUtilService.validateCredentialsForChangePassword(mockRequestDto));
    }

    @Test
    void shouldThrowRunTimeExceptionWhenCurrentPasswordNotValid() {
        var mockEmail = "mail@mail.com";
        var mockUserToReturn = User.builder()
                .firstName("First")
                .lastName("Last")
                .email(mockEmail)
                .password("Password123!@#")
                .role(Role.ADMIN)
                .build();

        var mockRequestDto = ChangePasswordRequestDto.builder()
                .email(mockEmail)
                .currentPassword("Password123123!")
                .newPassword("Pass123123123!@#")
                .confirmNewPassword("Pass123123123@!@#")
                .build();

        when(userRepository.findByEmail(mockEmail))
                .thenReturn(Optional.of(mockUserToReturn));

        assertThrows(RuntimeException.class,
                () ->userUtilService.validateCredentialsForChangePassword(mockRequestDto));
    }

    @Test
    void shouldThrowConfirmPasswordNotMatchExceptionWhenPasswordMismatch() {
        var mockEmail = "mail@mail.com";
        var mockUserToReturn = User.builder()
                .firstName("First")
                .lastName("Last")
                .email(mockEmail)
                .password("Password123!@#")
                .role(Role.ADMIN)
                .build();

        var mockRequestDto = ChangePasswordRequestDto.builder()
                .email(mockEmail)
                .currentPassword("Password123!@#")
                .newPassword("Pass123123123!@#")
                .confirmNewPassword("Pass12313@!@#")
                .build();

        when(userRepository.findByEmail(mockEmail))
                .thenReturn(Optional.of(mockUserToReturn));

        assertThrows(ConfirmPasswordNotMatchException.class,
                () -> userUtilService.validateCredentialsForChangePassword(mockRequestDto));
    }
}