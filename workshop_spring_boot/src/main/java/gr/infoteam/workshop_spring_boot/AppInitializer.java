package gr.infoteam.workshop_spring_boot;

import gr.infoteam.workshop_spring_boot.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AppInitializer implements CommandLineRunner {

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
    }
}
