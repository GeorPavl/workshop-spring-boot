package gr.infoteam.workshop_spring_boot.user.controllers;

import gr.infoteam.workshop_spring_boot.user.dtos.UserResponseDto;
import gr.infoteam.workshop_spring_boot.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity
                .ok()
                .body(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id")UUID id) {
        return ResponseEntity
                .ok()
                .body(userService.getById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable("email") String email) {
        return ResponseEntity
                .ok()
                .body(userService.getByEmail(email));
    }
}
