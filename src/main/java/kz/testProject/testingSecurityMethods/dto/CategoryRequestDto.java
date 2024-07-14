package kz.testProject.testingSecurityMethods.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDto {
    private String categoryName;
    private String categoryDescription;
//    private String categoryImageUrl;
    private String parentCategoryName;
}
