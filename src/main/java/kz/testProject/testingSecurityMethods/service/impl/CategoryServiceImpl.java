package kz.testProject.testingSecurityMethods.service.impl;

import kz.testProject.testingSecurityMethods.dto.CategoryDto;
import kz.testProject.testingSecurityMethods.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public void addCategory(String category, String description, String parentCategory) {

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return List.of();
    }

    @Override
    public CategoryDto getCategory(String category) {
        return null;
    }

    @Override
    public void deleteCategory(String category) {

    }

    @Override
    public CategoryDto updateCategory(String category, String description, String parentCategory, List<String> subCategories) {
        return null;
    }
}
