package gr.infoteam.workshop_spring_boot.features.user.services;

import gr.infoteam.workshop_spring_boot.features.user.User;
import gr.infoteam.workshop_spring_boot.features.user.dtos.UpdateUserRequestDto;
import gr.infoteam.workshop_spring_boot.features.user.mappers.UserMapper;
import gr.infoteam.workshop_spring_boot.features.user.dtos.UserRequestDto;
import gr.infoteam.workshop_spring_boot.features.user.dtos.UserResponseDto;
import gr.infoteam.workshop_spring_boot.features.user.repositories.UserRepository;
import gr.infoteam.workshop_spring_boot.utils.exceptions.custom.impls.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordUtilService passwordUtilService;

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .toList();
    }

    @Override
    public UserResponseDto getById(UUID id) {
        return new UserResponseDto(getEntityById(id));
    }

    @Override
    public User getEntityById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), "id",
                        id.toString()));
    }

    @Override
    public UserResponseDto getByEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto create(UserRequestDto requestDto) throws NoSuchAlgorithmException {
        var entity = userMapper.mapDtoToEntity(requestDto);
        passwordUtilService.encryptPassword(entity);
        var savedEntity = userRepository.save(entity);

        return new UserResponseDto(savedEntity);
    }

    @Override
    public UserResponseDto update(UUID id, UpdateUserRequestDto requestDto) {
        var existingUser = getEntityById(id);
        var mappedUser = userMapper.mapUpdateDtoToExistingEntity(requestDto, existingUser);
        var savedUser = userRepository.save(mappedUser);
        return new UserResponseDto(savedUser);
    }
}
