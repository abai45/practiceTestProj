package kz.testProject.testingSecurityMethods.service;

import kz.testProject.testingSecurityMethods.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    void addCategory(String category, String description, String parentCategory);
    void createSubCategory(String category, String description, String parentCategory);
    void addSubCategory(String category, List<String> subCategories);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategory(String category);
    void deleteCategory(String category);
    CategoryDto updateCategory(String category, String description, String parentCategory, List<String> subCategories);
}
