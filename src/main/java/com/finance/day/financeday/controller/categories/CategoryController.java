package com.finance.day.financeday.controller.categories;

import com.finance.day.financeday.records.categories.CategoryRecord;
import com.finance.day.financeday.services.categories.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRecord categoryRecord){
            return categoryService.createCategory(categoryRecord);
    }

    @PutMapping("/create")
    public ResponseEntity<?> editCategory(@RequestBody CategoryRecord categoryRecord){
        return categoryService.editCategory(categoryRecord);
    }
}
