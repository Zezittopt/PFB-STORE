package com.kreitek.store.infrastructure.rest;

import com.kreitek.store.application.dto.CategoryDto;
import com.kreitek.store.application.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryRestController {
    private CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @CrossOrigin
    @GetMapping(value = "/categories", produces = "application/json")
    ResponseEntity<List<CategoryDto>> getAllCategories(@RequestParam(name = "partialName", required = false) String partialName) {
        List<CategoryDto> categories;


        if (partialName == null) {
            categories = this.categoryService.getAllCategories();
        } else {
            categories = this.categoryService.getAllCategoriesByName(partialName);
        }

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/categories", produces = "application/json", consumes = "application/json")
    ResponseEntity<CategoryDto> insertCategory(@RequestBody CategoryDto categoryDto){
        categoryDto = this.categoryService.saveCategory(categoryDto);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }


}
