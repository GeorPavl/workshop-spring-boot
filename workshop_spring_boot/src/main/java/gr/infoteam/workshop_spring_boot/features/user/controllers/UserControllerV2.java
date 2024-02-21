package gr.infoteam.workshop_spring_boot.features.user.controllers;

import gr.infoteam.workshop_spring_boot.features.user.User;
import gr.infoteam.workshop_spring_boot.features.user.dtos.UserResponseDto;
import gr.infoteam.workshop_spring_boot.features.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class UserControllerV2 {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity
                .ok()
                .body(userRepository.findAll());
    }

    @GetMapping("/get-all-dtos")
    public ResponseEntity<List<UserResponseDto>> getAllUsersDtos() {
        return ResponseEntity
                .ok()
                .body(userRepository.findAll().stream()
                        .map(UserResponseDto::new)
                        .toList());
    }
}
