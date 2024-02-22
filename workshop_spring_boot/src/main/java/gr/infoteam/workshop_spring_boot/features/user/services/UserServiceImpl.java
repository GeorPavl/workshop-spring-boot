package gr.infoteam.workshop_spring_boot.features.user.services;

import gr.infoteam.workshop_spring_boot.features.skill.services.SkillService;
import gr.infoteam.workshop_spring_boot.features.skill.services.SkillUtilService;
import gr.infoteam.workshop_spring_boot.features.user.User;
import gr.infoteam.workshop_spring_boot.features.user.dtos.*;
import gr.infoteam.workshop_spring_boot.features.user.mappers.UserMapper;
import gr.infoteam.workshop_spring_boot.features.user.repositories.UserRepository;
import gr.infoteam.workshop_spring_boot.utils.exceptions.custom.implementations.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordUtilService passwordUtilService;
    private final UserUtilService userUtilService;
    private final SkillService skillService;
    private final SkillUtilService skillUtilService;

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
        return new UserResponseDto(getEntityByEmail(email));
    }

    @Override
    public User getEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @Override
    public UserResponseDto create(UserRequestDto requestDto) {
        var entity = userMapper.mapDtoToEntity(requestDto);
        passwordUtilService.encryptPassword(entity);
        var savedEntity = userRepository.save(entity);

        return new UserResponseDto(savedEntity);
    }

    @Override
    public UserResponseDto addSkillToUser(UserSkillRequestDto requestDto) {
        // Check if user exists
        var user = getEntityByEmail(requestDto.email());
        // Check if skill exists
        var skill = skillService.getEntityByName(requestDto.skill());
        // Check id user has already this skill
        skillUtilService.checkIfUserHasAlreadyThisSkill(user, skill);
        // Add bidirectional
        user.addSkill(skill);
        // Save use skill
        var savedUser = userRepository.save(user);
        // Return user dto
        return new UserResponseDto(savedUser);
    }

    @Override
    public UserResponseDto update(UUID id, UpdateUserRequestDto requestDto) {
        var existingUser = getEntityById(id);
        var mappedUser = userMapper.mapUpdateDtoToExistingEntity(requestDto, existingUser);
        var savedUser = userRepository.save(mappedUser);
        return new UserResponseDto(savedUser);
    }

    @Override
    public String changePassword(ChangePasswordRequestDto changePasswordRequestDto) {
        var user = getEntityByEmail(changePasswordRequestDto.email());
        // check if current pass is valid
        // check if new password equals confirm
        userUtilService.validateCredentialsForChangePassword(changePasswordRequestDto);
        // set new password
        user.setPassword(changePasswordRequestDto.newPassword());
        // encrypt new password
        passwordUtilService.encryptPassword(user);
        // save password
        userRepository.save(user);
        // return message
        return "Password updated successfully";
    }

    @Override
    public String delete(UUID id) {
        try {
            var entity = getEntityById(id);
            userRepository.deleteById(id);
            return String.format("User with id %s, was deleted successfully", id);
        } catch (Exception e) {
            return String.format("Something went wrong! %s", e.getMessage());
        }
    }
}
