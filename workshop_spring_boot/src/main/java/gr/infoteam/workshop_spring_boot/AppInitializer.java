package gr.infoteam.workshop_spring_boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AppInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("Hello World!");
        log.error("This is an error!");
    }
}
