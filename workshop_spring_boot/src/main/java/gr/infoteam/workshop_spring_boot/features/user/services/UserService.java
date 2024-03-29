package gr.infoteam.workshop_spring_boot.features.user.services;

import gr.infoteam.workshop_spring_boot.features.user.User;
import gr.infoteam.workshop_spring_boot.features.user.dtos.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserResponseDto> getAll();
    UserResponseDto getById(UUID id);
    User getEntityById(UUID id);
    UserResponseDto getByEmail(String email);
    User getEntityByEmail(String email);
    UserResponseDto create(UserRequestDto requestDto);
    UserResponseDto addSkillToUser(UserSkillRequestDto requestDto);
    UserResponseDto update(UUID id, UpdateUserRequestDto requestDto);
    String changePassword(ChangePasswordRequestDto changePasswordRequestDto);
    String delete(UUID id);
}
