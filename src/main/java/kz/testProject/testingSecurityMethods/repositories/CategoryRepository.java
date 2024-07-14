package kz.testProject.testingSecurityMethods.repositories;

import jakarta.transaction.Transactional;
import kz.testProject.testingSecurityMethods.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
}
