package gr.infoteam.workshop_spring_boot.user.controllers;

import gr.infoteam.workshop_spring_boot.user.User;
import gr.infoteam.workshop_spring_boot.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity
                .ok()
                .body(userRepository.findAll());
    }
}
