package com.ngleanhvu.controller;

import com.ngleanhvu.dto.CommentDTO;
import com.ngleanhvu.service.ICommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class CommentController {
    private ICommentService iCommentService;
    public CommentController(ICommentService iCommentService){
        this.iCommentService=iCommentService;
    }
    @PostMapping("posts/{id}/comments")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO,
                                                    @PathVariable(name = "id") Integer postId){
        return ResponseEntity
                .ok()
                .header("custom-header","nguyen-vu")
                .body(iCommentService.createComment(postId,commentDTO));
    }
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getAllCommentsByPostId(@PathVariable Integer postId){
        return ResponseEntity
                .ok()
                .header("custom-header","nguyen-vu")
                .body(iCommentService.getAllCommentsByPostId(postId));
    }
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentsById(@PathVariable(name = "id") Integer id,
                                                      @PathVariable(name = "postId") Integer postId){
        return ResponseEntity
                .ok()
                .header("custom-header","nguyen-vu")
                .body(iCommentService.getCommentById(postId, id));
    }
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable(name = "postId") Integer postId,
                                                     @PathVariable(name = "id") Integer id,
                                                     @Valid @RequestBody CommentDTO commentDTO){
        commentDTO.setId(id);
        return ResponseEntity
                .ok()
                .header("custom-header","nguyen-vu")
                .body(iCommentService.updateComment(postId,id,commentDTO));
    }
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(name = "postId") Integer postId,
                                                    @PathVariable(name = "id") Integer id){
        iCommentService.deleteCommentById(postId,id);
        return ResponseEntity
                .ok()
                .header("custom-header","nguyen-vu")
                .body("Delete successfully");
    }
}
