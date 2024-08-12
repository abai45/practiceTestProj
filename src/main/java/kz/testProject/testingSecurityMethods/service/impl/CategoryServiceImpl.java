package kz.testProject.testingSecurityMethods.service.impl;

import kz.testProject.testingSecurityMethods.dto.CategoryDto;
import kz.testProject.testingSecurityMethods.entities.CategoryEntity;
import kz.testProject.testingSecurityMethods.repositories.CategoryRepository;
import kz.testProject.testingSecurityMethods.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public void addCategory(String category, String description, String parentCategory) {
        var categoryList = categoryRepository.findAll();
        for (CategoryEntity cat: categoryList) {
            if(cat.getName().equals(category)) {
                throw new RuntimeException("Category already exists");
            }
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(category);
        categoryEntity.setDescription(description);
        if (parentCategory != null) {
            CategoryEntity parentCategoryEntity = getCategoryEntityByName(parentCategory);
            categoryEntity.setParentCategory(parentCategoryEntity);
        }
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void createSubCategory(String category, String description, String parentCategory) {
        var categoryList = categoryRepository.findAll();
        for (CategoryEntity cat: categoryList) {
            if(cat.getName().equals(category)) {
                throw new RuntimeException("Category already exists");
            }
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(category);
        categoryEntity.setDescription(description);
        categoryEntity.setParentCategory(getCategoryEntityByName(parentCategory));
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void addSubCategory(String category, List<String> subCategories) {
        CategoryEntity parentCategory = getCategoryEntityByName(category);
        for (String subCategory : subCategories) {
            CategoryEntity subCategoryEntity = getCategoryEntityByName(subCategory);
            subCategoryEntity.setParentCategory(parentCategory);
        }
        categoryRepository.save(parentCategory);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntities) {
            categoryDtos.add(toDto(categoryEntity));
        }
        return categoryDtos;
    }

    @Override
    public CategoryDto getCategory(String category) {
        CategoryEntity categoryEntity = getCategoryEntityByName(category);
        return toDto(categoryEntity);
    }

    @Override
    public void deleteCategory(String category) {
        CategoryEntity categoryEntity = getCategoryEntityByName(category);
        categoryRepository.delete(categoryEntity);
    }

    public CategoryDto updateCategory(String category, String description, String parentCategory, List<String> subCategories) {
        CategoryEntity categoryEntity = getCategoryEntityByName(category);
        categoryEntity.setDescription(description);
        if (parentCategory != null) {
            CategoryEntity parentCategoryEntity = getCategoryEntityByName(parentCategory);
            categoryEntity.setParentCategory(parentCategoryEntity);
        } else {
            categoryEntity.setParentCategory(null);
        }
        if (subCategories != null && !subCategories.isEmpty()) {
            for (String subCategory : subCategories) {
                CategoryEntity subCategoryEntity = getCategoryEntityByName(subCategory);
                subCategoryEntity.setParentCategory(categoryEntity);
                categoryEntity.getChildren().add(subCategoryEntity);
            }
        }
        categoryRepository.save(categoryEntity);
        return toDto(categoryEntity);
    }

    private CategoryEntity getCategoryEntityByName(String category) {
        return categoryRepository.findByName(category)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    private CategoryDto toDto(CategoryEntity category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setCategoryName(category.getName());
        dto.setCategoryFoods(category.getFoods()
                .stream()
                .map(food -> food.getName().toString())
                .toList());
        dto.setCategoryDescription(category.getDescription());
        dto.setParentCategory(category.getParentCategory() != null ? category.getParentCategory().getName() : null);
        dto.setSubCategories(category.getChildren()
                .stream()
                .map(categoryChild -> categoryChild.getName().toString())
                .toList());
        return dto;
    }

}
