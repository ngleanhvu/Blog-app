package com.ngleanhvu.service;

import com.ngleanhvu.dto.CategoryDTO;
import com.ngleanhvu.dto.PostDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Integer id);
    CategoryDTO updateCategory(CategoryDTO categoryDTO);
    void deleteCategoryById(Integer id);
}
