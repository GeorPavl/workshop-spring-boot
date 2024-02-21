package gr.infoteam.workshop_spring_boot.features.skill.controllers;

import gr.infoteam.workshop_spring_boot.features.skill.dtos.SkillRequestDto;
import gr.infoteam.workshop_spring_boot.features.skill.dtos.SkillResponseDto;
import gr.infoteam.workshop_spring_boot.features.skill.services.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<List<SkillResponseDto>> getAllSkills() {
        return ResponseEntity
                .ok()
                .body(skillService.getAll());
    }

    @PostMapping
    public ResponseEntity<SkillResponseDto> createSkill(@RequestBody @Valid SkillRequestDto requestDto) {
        return ResponseEntity
                .accepted()
                .body(skillService.create(requestDto));
    }
}
