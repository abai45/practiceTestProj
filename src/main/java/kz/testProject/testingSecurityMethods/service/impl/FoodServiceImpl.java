package kz.testProject.testingSecurityMethods.service.impl;

import kz.testProject.testingSecurityMethods.dto.FoodsDto;
import kz.testProject.testingSecurityMethods.service.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Override
    public void addFood(String name, String description, String price, String categoryName) {

    }

    @Override
    public List<FoodsDto> getFoods() {
        return List.of();
    }

    @Override
    public FoodsDto getFoodById(Long id) {
        return null;
    }

    @Override
    public void deleteFoodById(Long id) {

    }

    @Override
    public FoodsDto updateFood(FoodsDto food) {
        return null;
    }
}
