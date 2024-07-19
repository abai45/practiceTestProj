package kz.testProject.testingSecurityMethods.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FoodRequestDto {
    private String foodName;
    private String foodDescription;
//    private String foodImage;
    private BigDecimal foodPrice;
    private String foodCategory;
}
