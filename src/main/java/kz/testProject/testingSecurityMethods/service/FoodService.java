package kz.testProject.testingSecurityMethods.service;

import kz.testProject.testingSecurityMethods.dto.FoodsDto;

import java.util.List;

public interface FoodService {
    void addFood(String name, String description, String price, String categoryName);
    List<FoodsDto> getFoods();
    FoodsDto getFoodById(Long id);
    void deleteFoodById(Long id);
    FoodsDto updateFood(FoodsDto food);
}
