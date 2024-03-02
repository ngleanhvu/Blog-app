package com.ngleanhvu.dto;

import com.ngleanhvu.entity.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Integer id;
    @NotEmpty
    @Size(min = 2, message = "Category name should have at least 2 characters")
    private String name;
    private String description;
    private List<PostDTO> posts;
}
