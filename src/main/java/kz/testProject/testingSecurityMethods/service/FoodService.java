package kz.testProject.testingSecurityMethods.service;

import kz.testProject.testingSecurityMethods.dto.FoodsDto;

import java.math.BigDecimal;
import java.util.List;

public interface FoodService {
    void addFood(String name, String description, BigDecimal price, String categoryName);
    List<FoodsDto> getFoods();
    FoodsDto getFoodById(Long id);
    void deleteFoodById(Long id);
    FoodsDto updateFood(String name, String description, BigDecimal price, String categoryName);
}
