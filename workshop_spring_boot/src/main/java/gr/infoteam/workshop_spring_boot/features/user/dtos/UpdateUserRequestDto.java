package gr.infoteam.workshop_spring_boot.features.user.dtos;

public record UpdateUserRequestDto(
        String role,
        String phoneNumber,
        Boolean isAvailable,
        String location,
        String jobRole
) {
}
