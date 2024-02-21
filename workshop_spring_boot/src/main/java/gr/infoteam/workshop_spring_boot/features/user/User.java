package gr.infoteam.workshop_spring_boot.features.user;

import gr.infoteam.workshop_spring_boot.features.skill.Skill;
import gr.infoteam.workshop_spring_boot.features.user_info.UserInfo;
import gr.infoteam.workshop_spring_boot.features.user.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_skills",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    private List<Skill> skills;
}
