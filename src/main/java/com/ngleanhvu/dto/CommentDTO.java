package com.ngleanhvu.dto;

import com.ngleanhvu.entity.Post;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Integer id;
    @NotEmpty(message = "Comment name should not be empty")
    private String name;
    @NotEmpty(message = "Comment name should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Comment name should not be empty")
    @Size(min = 10, message = "Comment body should have at least 10 characters")
    private String body;
}
