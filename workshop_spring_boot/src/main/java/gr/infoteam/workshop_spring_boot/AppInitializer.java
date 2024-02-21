package gr.infoteam.workshop_spring_boot;

import gr.infoteam.workshop_spring_boot.user.repositories.UserRepository;
import gr.infoteam.workshop_spring_boot.user.enums.Role;
import gr.infoteam.workshop_spring_boot.user.User;
import gr.infoteam.workshop_spring_boot.user_info.UserInfo;
import gr.infoteam.workshop_spring_boot.user_info.enums.JobRole;
import gr.infoteam.workshop_spring_boot.user_info.enums.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AppInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        createUsers();
        printSaveUsers();
    }

    private void createUsers() {
        try {
            var mary = User.builder()
                    .firstName("Mary")
                    .lastName("Public")
                    .email("mary@mail.com")
                    .password("Password123!@#")
                    .role(Role.ADMIN)
                    .userInfo(UserInfo.builder()
                            .phoneNumber("6986002233")
                            .isAvailable(true)
                            .location(Location.THESSALONIKI)
                            .jobRole(JobRole.MANAGER)
                            .build())
                    .build();

            var jim = User.builder()
                    .firstName("Jim")
                    .lastName("Doe")
                    .email("jim@mail.com")
                    .password("Password123!@#")
                    .role(Role.USER)
                    .userInfo(UserInfo.builder()
                            .phoneNumber("6974563210")
                            .isAvailable(false)
                            .location(Location.BUBENREUTH)
                            .jobRole(JobRole.DEVELOPER)
                            .build())
                    .build();

            var secondMary = User.builder()
                            .firstName("Mary")
                            .lastName("Another")
                            .email("mary.another@mail.com")
                            .password("Password123!@#")
                            .role(Role.USER)
                            .userInfo(UserInfo.builder()
                                    .phoneNumber("98546321456")
                                    .isAvailable(false)
                                    .location(Location.DORTMUND)
                                    .jobRole(JobRole.PROJECT_MANAGER)
                                    .build())
                            .build();

            userRepository.save(mary);
            userRepository.save(jim);
            userRepository.save(secondMary);

            log.info("Users saved successfully");
        } catch (Exception e) {
            log.error("Something went wrong");
            log.error(e.getMessage());
        }
    }

    private void printSaveUsers() {
        userRepository.findAll().forEach((user -> log.info(user.toString())));
    }
}
