package com.ngleanhvu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Schema(
        name = "PostDTO Model Information"
)
public class PostDTO {
    private Integer id;
    @Schema(
            name = "PostDTO Model Title"
    )
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;
    @Schema(
            name = "PostDTO Model Description"
    )
    @NotEmpty
    @Size(min = 10, message = "Post title should have at least 10 characters")
    private String description;
    @Schema(
            name = "PostDTO Model Content"
    )
    @NotEmpty
    private String content;
    private List<CommentDTO> comments;
    @Schema(
            name = "PostDTO Model Category"
    )
    private Integer categoryId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
