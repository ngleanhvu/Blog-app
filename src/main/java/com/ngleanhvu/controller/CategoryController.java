package com.ngleanhvu.controller;

import com.ngleanhvu.dto.CategoryDTO;
import com.ngleanhvu.service.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private ICategoryService iCategoryService;
    public CategoryController(ICategoryService iCategoryService){
        this.iCategoryService=iCategoryService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity
                .ok()
                .header("custom-header","nguyen-vu")
                .body(iCategoryService.createCategory(categoryDTO));
    }
}
