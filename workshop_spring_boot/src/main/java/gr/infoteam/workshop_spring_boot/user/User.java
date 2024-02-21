package gr.infoteam.workshop_spring_boot.user;

import gr.infoteam.workshop_spring_boot.user.enums.Role;
import gr.infoteam.workshop_spring_boot.user_info.UserInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id", referencedColumnName = "id", columnDefinition = "uuid")
    private UserInfo userInfo;
}
