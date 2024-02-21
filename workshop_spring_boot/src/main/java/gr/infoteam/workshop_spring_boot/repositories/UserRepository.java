package gr.infoteam.workshop_spring_boot.repositories;

import gr.infoteam.workshop_spring_boot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE last_name LIKE 'D%'", nativeQuery = true)
    List<User> findUsersWithLastNameStartingWithD();
}
