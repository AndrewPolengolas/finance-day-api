package com.finance.day.financeday.controller.categories;

import com.finance.day.financeday.records.categories.CategoryRecord;
import com.finance.day.financeday.services.categories.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryRecord categoryRecord){
            return categoryService.createCategory(categoryRecord);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editCategory(@PathVariable("id") Long id, @RequestBody CategoryRecord categoryRecord){
        return categoryService.editCategory(id, categoryRecord);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listAllCategories(){
        return categoryService.listAllCategories();
    }
}
