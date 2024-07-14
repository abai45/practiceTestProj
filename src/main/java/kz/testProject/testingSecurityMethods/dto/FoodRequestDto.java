package kz.testProject.testingSecurityMethods.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodRequestDto {
    private String foodName;
    private String foodDescription;
//    private String foodImage;
    private String foodPrice;
    private String foodCategory;
}
