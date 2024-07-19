package kz.testProject.testingSecurityMethods.controller;

import kz.testProject.testingSecurityMethods.dto.CategoryDto;
import kz.testProject.testingSecurityMethods.handler.AppError;
import kz.testProject.testingSecurityMethods.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("{categoryName}")
    public ResponseEntity<?> getCategory(@PathVariable(name = "categoryName") String categoryName) {
        try{
            var category = categoryService.getCategory(categoryName);
            return new ResponseEntity<>(category, OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new AppError(NOT_FOUND.value(),
                    "Category with name "+categoryName+" not found"),
                    NOT_FOUND);
        }
    }
    @GetMapping()
    public ResponseEntity<?> getAllCategories() {
        try {
            var categories = categoryService.getAllCategories();
            return new ResponseEntity<>(categories, OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new AppError(NOT_FOUND.value(),
                    "Categories not found"), NOT_FOUND);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto) {
        try {
            categoryService.addCategory(categoryDto.getCategoryName(), categoryDto.getCategoryDescription(), categoryDto.getParentCategory());
            return new ResponseEntity<>("Category added successfully", OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new AppError(NOT_FOUND.value(),
                    "Categories not found"), NOT_FOUND);
        }
    }
    @PostMapping("/remove{categoryName}")
    public ResponseEntity<?> removeCategory(@PathVariable(name = "categoryName") String categoryName) {
        try {
            categoryService.deleteCategory(categoryName);
            return new ResponseEntity<>("Category removed successfully", OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new AppError(NOT_FOUND.value(),
                    "Category with name " + categoryName + " not found"),
                    NOT_FOUND);
        }
    }
    @PostMapping("/update{categoryName}")
    public ResponseEntity<?> updateCategory(@PathVariable(name = "categoryName") String categoryName, @RequestBody CategoryDto categoryDto) {
        try {
            var category = categoryService.updateCategory(categoryName, categoryDto.getCategoryDescription(), categoryDto.getParentCategory(), categoryDto.getSubCategories());
            return new ResponseEntity<>(category, OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new AppError(NOT_FOUND.value(),
                    "Category with name "+categoryName+" not found"),
                    NOT_FOUND);
        }
    }
    @PostMapping("/create{subCategoryName}")
    public ResponseEntity<?> createCategory(@PathVariable(name = "subCategoryName") String subCategoryName, @RequestBody CategoryDto categoryDto) {
        try {
            categoryService.createSubCategory(subCategoryName, categoryDto.getCategoryDescription(), categoryDto.getParentCategory());
            return new ResponseEntity<>("SubCategory created successfully", OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new AppError(NOT_FOUND.value(),
                    exception.getMessage()), NOT_FOUND);
        }
    }
    @PostMapping("/addSub{categoryName}")
    public ResponseEntity<?> addSubCategory(@PathVariable(name = "categoryName") String categoryName, @RequestBody CategoryDto categoryDto) {
        try {
            categoryService.addSubCategory(categoryName, categoryDto.getSubCategories());
            return new ResponseEntity<>("SubCategory added successfully", OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new AppError(NOT_FOUND.value(), exception.getMessage()), NOT_FOUND);
        }
    }
}
