package gr.infoteam.workshop_spring_boot.user.dtos;

public record UserRequestDto(
        String firstName,
        String lastName,
        String email,
        String password,
        String role,
        String phoneNumber,
        Boolean isAvailable,
        String location,
        String jobRole
) {
}
