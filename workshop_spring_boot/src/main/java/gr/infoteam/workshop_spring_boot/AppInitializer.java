package gr.infoteam.workshop_spring_boot;

import gr.infoteam.workshop_spring_boot.repositories.UserRepository;
import gr.infoteam.workshop_spring_boot.user.Role;
import gr.infoteam.workshop_spring_boot.user.User;
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
    }

    private void createUsers() {
        try {
            var mary = User.builder()
                    .firstName("Mary")
                    .lastName("Public")
                    .email("mary@mail.com")
                    .password("Password123!@#")
                    .role(Role.ADMIN)
                    .build();

            var jim = User.builder()
                    .firstName("Jim")
                    .lastName("Doe")
                    .email("jim@mail.com")
                    .password("Password123!@#")
                    .role(Role.USER)
                    .build();

            var secondMary = User.builder()
                            .firstName("Mary")
                            .lastName("Another")
                            .email("mary.another@mail.com")
                            .password("Password123!@#")
                            .role(Role.USER)
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
}
