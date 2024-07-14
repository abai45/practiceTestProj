package kz.testProject.testingSecurityMethods.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FoodsDto {
    private Long id;
    private String foodName;
    private String foodDescription;
    private BigDecimal foodPrice;
    private String category;
}
