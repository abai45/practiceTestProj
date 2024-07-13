package kz.testProject.testingSecurityMethods.repositories;

import jakarta.transaction.Transactional;
import kz.testProject.testingSecurityMethods.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    PermissionEntity findByName(String name);
}
