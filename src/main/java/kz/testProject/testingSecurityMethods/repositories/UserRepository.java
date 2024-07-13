package kz.testProject.testingSecurityMethods.repositories;

import jakarta.transaction.Transactional;
import kz.testProject.testingSecurityMethods.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
