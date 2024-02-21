package gr.infoteam.workshop_spring_boot.features.user_info.repositories;

import gr.infoteam.workshop_spring_boot.features.user_info.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
}
