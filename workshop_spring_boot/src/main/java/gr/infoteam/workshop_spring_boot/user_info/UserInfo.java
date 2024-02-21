package gr.infoteam.workshop_spring_boot.user_info;

import gr.infoteam.workshop_spring_boot.user_info.enums.JobRole;
import gr.infoteam.workshop_spring_boot.user_info.enums.Location;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users_info")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(name = "location")
    @Enumerated(value = EnumType.STRING)
    private Location location;

    @Column(name = "job_role")
    @Enumerated(value = EnumType.STRING)
    private JobRole jobRole;

}
