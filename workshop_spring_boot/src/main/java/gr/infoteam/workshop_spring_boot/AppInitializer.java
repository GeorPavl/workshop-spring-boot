package gr.infoteam.workshop_spring_boot;

import gr.infoteam.workshop_spring_boot.repositories.UserRepository;
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
        User userWithConstructor = new User(
                "Mary",
                "Doe",
                "mary@mail.com",
                "Password123!@#"
        );

        var userWithSetters = new User();
        userWithSetters.setFirstName("Jim");
        userWithSetters.setLastName("Public");
        userWithSetters.setEmail("jim@mail.com");
        userWithSetters.setPassword("Password123!@#");

        log.info("User with constructor: " + userWithConstructor);
        log.info("User with setters: " + userWithSetters);

        var savedUser = userRepository.save(userWithConstructor);
        log.info("Saved user: " + savedUser);

        var retrievedUserById = userRepository.findById(savedUser.getId());
        if (retrievedUserById.isPresent()) {
            log.info("Retrieve user by id: " + retrievedUserById);
        } else {
            log.info("User not found");
        }

        var retrievedUserByEmail = userRepository.findByEmail(savedUser.getEmail());
        if (retrievedUserByEmail.isPresent()) {
            log.info("Retrieve user by email " + retrievedUserByEmail);
        } else {
            log.info("User not found");
        }

        userRepository.save(userWithSetters);

        log.info(userRepository.findAll().toString());
    }
}
