package kz.testProject.testingSecurityMethods.repositories;

import jakarta.transaction.Transactional;
import kz.testProject.testingSecurityMethods.entities.CategoryEntity;
import kz.testProject.testingSecurityMethods.entities.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface FoodsRepository extends JpaRepository<FoodEntity, Long> {
    Optional<FoodEntity> findByName(String name);

}
