package gr.infoteam.workshop_spring_boot.features.user.services;

import gr.infoteam.workshop_spring_boot.features.user.User;
import gr.infoteam.workshop_spring_boot.features.user.dtos.ChangePasswordRequestDto;
import gr.infoteam.workshop_spring_boot.features.user.repositories.UserRepository;
import gr.infoteam.workshop_spring_boot.utils.exceptions.custom.implementations.ConfirmPasswordNotMatchException;
import gr.infoteam.workshop_spring_boot.utils.exceptions.custom.implementations.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUtilService {

    private final UserRepository userRepository;

    public void validCredentialsForChangePassword(ChangePasswordRequestDto requestDto) {
        var existingUser = checkIfUserExistsAndReturnUser(requestDto.email());
        checkIfPasswordIsValid(requestDto.currentPassword(), existingUser);
        checkIfConfirmPasswordMatchesNew(requestDto);
    }

    private User checkIfUserExistsAndReturnUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(),
                        "email", email));
    }

    private void checkIfPasswordIsValid(String password, User existingUser) {
        if (!password.equals(existingUser.getPassword())) {
            throw new RuntimeException("Password is not valid");
        }
    }

    private void checkIfConfirmPasswordMatchesNew(ChangePasswordRequestDto requestDto) {
        if (!requestDto.newPassword().equals(requestDto.confirmNewPassword())) {
            throw new ConfirmPasswordNotMatchException("Passwords don't match");
        }
    }
}
