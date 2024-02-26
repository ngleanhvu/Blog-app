package com.ngleanhvu.controller;

import com.ngleanhvu.dto.PostDTO;
import com.ngleanhvu.service.IPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private IPostService iPostService;

    public PostController(IPostService iPostService) {
        this.iPostService = iPostService;
    }

    @PostMapping()
    public ResponseEntity<PostDTO> createPost(@RequestBody() PostDTO postDTO) {
        return ResponseEntity
                .ok()
                .header("custom-header", "nguyen-vu")
                .body(iPostService.createPost(postDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostDTOById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity
                .ok()
                .header("custom-header", "nguyen-vu")
                .body(iPostService.getPostById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePostDTO(@PathVariable(name = "id") Integer id, @RequestBody PostDTO postDTO) {
        postDTO.setId(id);
        return ResponseEntity
                .ok()
                .header("custom-header", "nguyen-vu")
                .body(iPostService.updatePost(postDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostDTOById(@PathVariable(name = "id") Integer id){
        iPostService.deletePostById(id);
        return ResponseEntity
                .ok()
                .header("custom-header", "nguyen-vu")
                .body("Delete successfully");
    }
}
