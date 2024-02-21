package gr.infoteam.workshop_spring_boot.features.user.mappers;

import gr.infoteam.workshop_spring_boot.features.user_info.UserInfo;
import gr.infoteam.workshop_spring_boot.features.user_info.enums.JobRole;
import gr.infoteam.workshop_spring_boot.features.user_info.enums.Location;
import gr.infoteam.workshop_spring_boot.features.user.User;
import gr.infoteam.workshop_spring_boot.features.user.dtos.UserRequestDto;
import gr.infoteam.workshop_spring_boot.features.user.enums.Role;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapDtoToEntity(UserRequestDto requestDto) {
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

        return entity;
    }
}
