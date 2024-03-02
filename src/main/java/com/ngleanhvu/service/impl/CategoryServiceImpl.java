package com.ngleanhvu.service.impl;

import com.ngleanhvu.dto.CategoryDTO;
import com.ngleanhvu.entity.Category;
import com.ngleanhvu.execption.ResourceNotFoundException;
import com.ngleanhvu.repository.CategoryRepository;
import com.ngleanhvu.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ModelMapper modelMapper){
        this.categoryRepository=categoryRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category=modelMapper.map(categoryDTO,Category.class);
        categoryRepository.save(category);
        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream().map(u->modelMapper.map(u,CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        Category category=categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category","id",id.toString()));
        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category existingCategory=categoryRepository.findById(categoryDTO.getId())
                .orElseThrow(()->new ResourceNotFoundException("Category",
                        "id", categoryDTO.getId().toString()));
        update(existingCategory,categoryDTO);
        categoryRepository.save(existingCategory);
        return modelMapper.map(existingCategory,CategoryDTO.class);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        Category category=categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category","id",id.toString()));
        categoryRepository.delete(category);
    }

    private void update(Category existingCategory, CategoryDTO categoryDTO){
        String name=categoryDTO.getName();
        String description=categoryDTO.getDescription();
        if(name!=null) existingCategory.setName(name);
        if(description!=null) existingCategory.setDescription(description);
    }
}
