package gr.infoteam.workshop_spring_boot.features.user.services;

import gr.infoteam.workshop_spring_boot.features.user.User;
import gr.infoteam.workshop_spring_boot.features.user.dtos.UpdateUserRequestDto;
import gr.infoteam.workshop_spring_boot.features.user.dtos.UserRequestDto;
import gr.infoteam.workshop_spring_boot.features.user.dtos.UserResponseDto;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserResponseDto> getAll();
    UserResponseDto getById(UUID id);
    User getEntityById(UUID id);
    UserResponseDto getByEmail(String email);
    UserResponseDto create(UserRequestDto requestDto) throws NoSuchAlgorithmException;
    UserResponseDto update(UUID id, UpdateUserRequestDto requestDto);
}
