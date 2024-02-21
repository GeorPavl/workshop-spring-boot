package gr.infoteam.workshop_spring_boot.user.services;

import gr.infoteam.workshop_spring_boot.user.User;
import gr.infoteam.workshop_spring_boot.user.dtos.UserRequestDto;
import gr.infoteam.workshop_spring_boot.user.dtos.UserResponseDto;
import gr.infoteam.workshop_spring_boot.user.enums.Role;
import gr.infoteam.workshop_spring_boot.user.repositories.UserRepository;
import gr.infoteam.workshop_spring_boot.user_info.UserInfo;
import gr.infoteam.workshop_spring_boot.user_info.enums.JobRole;
import gr.infoteam.workshop_spring_boot.user_info.enums.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .toList();
    }

    @Override
    public UserResponseDto getById(UUID id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto getByEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto create(UserRequestDto requestDto) throws NoSuchAlgorithmException {
        // Convert dto to entity
        var entity = User.builder()
                .firstName(requestDto.firstName())
                .lastName(requestDto.lastName())
                .email(requestDto.email())
                .password(requestDto.password())
                .role(Role.valueOf(requestDto.role()))
                .userInfo(UserInfo.builder()
                        .phoneNumber(requestDto.phoneNumber())
                        .isAvailable(requestDto.isAvailable())
                        .jobRole(JobRole.valueOf(requestDto.jobRole()))
                        .location(Location.valueOf(requestDto.location()))
                        .build())
                .build();
        // Encrypt password
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(entity.getPassword().getBytes());
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        entity.setPassword(hexString.toString());
        // save user
        var savedEntity = userRepository.save(entity);

        // return user
        return new UserResponseDto(savedEntity);
    }
}
