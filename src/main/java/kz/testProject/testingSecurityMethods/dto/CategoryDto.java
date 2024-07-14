package kz.testProject.testingSecurityMethods.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String categoryName;
    private String categoryDescription;
    private List<String> categoryFoods;
    private String parentCategory;
    private List<String> subCategories;
}
