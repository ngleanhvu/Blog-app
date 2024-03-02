package com.ngleanhvu.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Integer id;
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Post title should have at least 10 characters")
    private String description;
    @NotEmpty
    private String content;
    private List<CommentDTO> comments;
    private Integer categoryId;
}
