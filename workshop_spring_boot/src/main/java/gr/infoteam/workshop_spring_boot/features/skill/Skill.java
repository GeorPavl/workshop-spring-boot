package gr.infoteam.workshop_spring_boot.features.skill;

import gr.infoteam.workshop_spring_boot.features.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "skills")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "skills", fetch = FetchType.EAGER)
    private List<User> users;
}
