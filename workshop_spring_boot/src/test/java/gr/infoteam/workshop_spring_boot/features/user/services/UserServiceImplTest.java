package gr.infoteam.workshop_spring_boot.features.user.services;

import gr.infoteam.workshop_spring_boot.features.skill.Skill;
import gr.infoteam.workshop_spring_boot.features.skill.repositories.SkillRepository;
import gr.infoteam.workshop_spring_boot.features.skill.services.SkillService;
import gr.infoteam.workshop_spring_boot.features.skill.services.SkillUtilService;
import gr.infoteam.workshop_spring_boot.features.user.User;
import gr.infoteam.workshop_spring_boot.features.user.dtos.UserRequestDto;
import gr.infoteam.workshop_spring_boot.features.user.dtos.UserSkillRequestDto;
import gr.infoteam.workshop_spring_boot.features.user.enums.Role;
import gr.infoteam.workshop_spring_boot.features.user.mappers.UserMapper;
import gr.infoteam.workshop_spring_boot.features.user.repositories.UserRepository;
import gr.infoteam.workshop_spring_boot.features.user_info.UserInfo;
import gr.infoteam.workshop_spring_boot.features.user_info.enums.JobRole;
import gr.infoteam.workshop_spring_boot.features.user_info.enums.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Tag("Unit_Test")
@ExtendWith(MockitoExtension.class)
@DisplayName("User service unit test")
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordUtilService passwordUtilService;
    @Mock
    private UserMapper userMapper;
    @Mock
    private SkillRepository skillRepository;
    @Mock
    private SkillUtilService skillUtilService;
    @Mock
    private SkillService skillService;

    @Test
    void shouldCreateUserSuccessfully() {
        // Arrange
        var mockRequestDto = UserRequestDto.builder()
                .firstName("Anna")
                .lastName("Public")
                .email("anna@mail.com")
                .password("Password123!@#")
                .role("USER")
                .phoneNumber("123123123123")
                .isAvailable(true)
                .location("THESSALONIKI")
                .jobRole("MANAGER")
                .build();

        var mockMappedUser = User.builder()
                .firstName("Anna")
                .lastName("Public")
                .email("anna@mail.com")
                .password("Password123!@#")
                .role(Role.USER)
                .userInfo(UserInfo.builder()
                        .phoneNumber("123123123123")
                        .isAvailable(true)
                        .location(Location.THESSALONIKI)
                        .jobRole(JobRole.MANAGER)
                        .build()
                )
                .build();

        var expectedSavedUser = mockMappedUser;
        expectedSavedUser.setId(UUID.randomUUID());

        when(userMapper.mapDtoToEntity(mockRequestDto)).thenReturn(mockMappedUser);
        when(userRepository.save(mockMappedUser)).thenReturn(expectedSavedUser);

        // Act
        var result = userService.create(mockRequestDto);

        // Assert
        assertNotNull(result);
        assertEquals(expectedSavedUser.getId(), result.id());
        assertEquals(expectedSavedUser.getEmail(), result.email());

        verify(userMapper).mapDtoToEntity(mockRequestDto);
        verify(passwordUtilService).encryptPassword(mockMappedUser);
        verify(userRepository).save(mockMappedUser);
    }

    @Test
    void shouldAddSkillToUserSuccessfully() {
        var userEmail = "mail@mail.com";
        var skillName = "MySkill";

        var mockRequestDto = UserSkillRequestDto.builder()
                .email(userEmail)
                .skill(skillName)
                .build();

        when(skillRepository.findByName(skillName))
                .thenReturn(Optional.of(
                        Skill.builder()
                                .name(skillName)
                                .build()
                ));

        when(userRepository.findByEmail(userEmail))
                .thenReturn(
                        Optional.of(
                                User.builder()
                                        .id(UUID.randomUUID())
                                        .email(userEmail)
                                        .build()
                        )
                );

        var result = userService.addSkillToUser(mockRequestDto);

        assertEquals(result.skills().size(), 1);
    }


}