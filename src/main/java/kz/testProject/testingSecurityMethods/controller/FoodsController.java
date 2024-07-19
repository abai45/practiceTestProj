package kz.testProject.testingSecurityMethods.controller;

import kz.testProject.testingSecurityMethods.dto.FoodRequestDto;
import kz.testProject.testingSecurityMethods.handler.AppError;
import kz.testProject.testingSecurityMethods.repositories.FoodsRepository;
import kz.testProject.testingSecurityMethods.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
public class FoodsController {
    private final FoodService foodService;
    @GetMapping("/")
    public ResponseEntity<?> getFood() {
        try {
            var foods = foodService.getFoods();
            return new ResponseEntity<>(foods, OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new AppError(BAD_REQUEST.value(), exception.getMessage()), BAD_REQUEST);
        }
    }
    @GetMapping("/get{id}")
    public ResponseEntity<?> getFoodById(@PathVariable Long id) {
        try {
            var food = foodService.getFoodById(id);
            return new ResponseEntity<>(food, OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new AppError(BAD_REQUEST.value(), exception.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addFood(@RequestBody FoodRequestDto foodRequestDto) {
        try {
            foodService.addFood(foodRequestDto.getFoodName(),foodRequestDto.getFoodDescription(),foodRequestDto.getFoodPrice(),foodRequestDto.getFoodCategory());
            return new ResponseEntity<>("Food added successfully", OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new AppError(BAD_REQUEST.value(), exception.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/delete{id}")
    public ResponseEntity<?> deleteFood(@PathVariable Long id) {
        try {
            foodService.deleteFoodById(id);
            return new ResponseEntity<>("Food deleted successfully", OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new AppError(BAD_REQUEST.value(), exception.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateFood(@RequestBody FoodRequestDto foodRequestDto) {
        try {
            var food = foodService.updateFood(foodRequestDto.getFoodName(), foodRequestDto.getFoodDescription(), foodRequestDto.getFoodPrice(),foodRequestDto.getFoodCategory());
            return new ResponseEntity<>(food, OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new AppError(BAD_REQUEST.value(), exception.getMessage()), BAD_REQUEST);
        }
    }
}
