package kz.testProject.testingSecurityMethods.service.impl;

import kz.testProject.testingSecurityMethods.dto.FoodsDto;
import kz.testProject.testingSecurityMethods.entities.CategoryEntity;
import kz.testProject.testingSecurityMethods.entities.FoodEntity;
import kz.testProject.testingSecurityMethods.repositories.CategoryRepository;
import kz.testProject.testingSecurityMethods.repositories.FoodsRepository;
import kz.testProject.testingSecurityMethods.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final CategoryRepository categoryRepository;
    private final FoodsRepository foodsRepository;

    @Override
    public void addFood(String name, String description, BigDecimal price, String categoryName) {
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setName(name);
        foodEntity.setDescription(description);
        foodEntity.setPrice(price);
        foodEntity.setCategory(getCategoryByName(categoryName));
        foodsRepository.save(foodEntity);
    }

    @Override
    public List<FoodsDto> getFoods() {
        List<FoodsDto> foodsDtoList = new ArrayList<>();
        for (FoodEntity foodEntity : foodsRepository.findAll()) {
            foodsDtoList.add(toDto(foodEntity));
        }
        return foodsDtoList;
    }

    @Override
    public FoodsDto getFoodById(Long id) {
        return toDto(getFoodEntityById(id));
    }

    @Override
    public void deleteFoodById(Long id) {
        foodsRepository.deleteById(id);
    }

    @Override
    public FoodsDto updateFood(String name, String description, BigDecimal price, String categoryName) {
        FoodEntity foodEntity = getFoodEntityByName(name);
        foodEntity.setName(name);
        foodEntity.setDescription(description);
        foodEntity.setPrice(price);
        foodEntity.setCategory(getCategoryByName(categoryName));
        foodsRepository.save(foodEntity);
        return toDto(foodEntity);
    }

    private CategoryEntity getCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
    private FoodEntity getFoodEntityById(Long id) {
        return foodsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));
    }
    private FoodEntity getFoodEntityByName(String name) {
        return foodsRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Food not found"));
    }
    private FoodsDto toDto(FoodEntity food) {
        FoodsDto foodDto = new FoodsDto();
        foodDto.setId(food.getId());
        foodDto.setFoodName(food.getName());
        foodDto.setFoodDescription(food.getDescription());
        foodDto.setFoodPrice(food.getPrice());
        return foodDto;
    }
}
