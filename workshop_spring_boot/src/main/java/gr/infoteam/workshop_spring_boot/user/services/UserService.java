package gr.infoteam.workshop_spring_boot.user.services;

import gr.infoteam.workshop_spring_boot.user.dtos.UserRequestDto;
import gr.infoteam.workshop_spring_boot.user.dtos.UserResponseDto;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserResponseDto> getAll();
    UserResponseDto getById(UUID id);
    UserResponseDto getByEmail(String email);
    UserResponseDto create(UserRequestDto requestDto) throws NoSuchAlgorithmException;
}
