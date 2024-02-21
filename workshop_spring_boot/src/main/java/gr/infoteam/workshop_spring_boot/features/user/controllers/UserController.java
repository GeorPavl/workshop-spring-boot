package gr.infoteam.workshop_spring_boot.features.user.controllers;

import gr.infoteam.workshop_spring_boot.features.user.dtos.*;
import gr.infoteam.workshop_spring_boot.features.user.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
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

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto requestDto) {
        return ResponseEntity
                .accepted()
                .body(userService.create(requestDto));
    }

    @PostMapping("/add-skill")
    public ResponseEntity<UserResponseDto> addSkillToUser(@RequestBody @Valid UserSkillRequestDto requestDto) {
        return ResponseEntity
                .accepted()
                .body(userService.addSkillToUser(requestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") UUID id,
                                                      @RequestBody @Valid UpdateUserRequestDto requestDto) {
        return ResponseEntity
                .accepted()
                .body(userService.update(id, requestDto));
    }

    @PatchMapping
    public ResponseEntity<String> changeUserPassword(@RequestBody @Valid ChangePasswordRequestDto requestDto) {
        return ResponseEntity
                .accepted()
                .body(userService.changePassword(requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id) {
        return ResponseEntity
                .ok()
                .body(userService.delete(id));
    }
}
