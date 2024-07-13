package kz.testProject.testingSecurityMethods.repositories;

import jakarta.transaction.Transactional;
import kz.testProject.testingSecurityMethods.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
