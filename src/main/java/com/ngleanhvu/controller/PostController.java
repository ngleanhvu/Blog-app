package com.ngleanhvu.controller;

import com.ngleanhvu.dto.PostDTO;
import com.ngleanhvu.entity.Post;
import com.ngleanhvu.service.IPostService;
import com.ngleanhvu.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private IPostService iPostService;

    public PostController(IPostService iPostService) {
        this.iPostService = iPostService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<PostDTO> createPost(@Valid  @RequestBody() PostDTO postDTO) {
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
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePostDTO(@PathVariable(name = "id") Integer id,
                                                 @Valid @RequestBody PostDTO postDTO) {
        postDTO.setId(id);
        return ResponseEntity
                .ok()
                .header("custom-header", "nguyen-vu")
                .body(iPostService.updatePost(postDTO));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostDTOById(@PathVariable(name = "id") Integer id){
        iPostService.deletePostById(id);
        return ResponseEntity
                .ok()
                .header("custom-header", "nguyen-vu")
                .body("Delete successfully");
    }
    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPosts(@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                     @RequestParam(value = "pageNo", defaultValue = AppConstants.PAGE_NO, required = false) Integer pageNo,
                                                     @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                     @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir

    ){
        return ResponseEntity
                .ok()
                .header("custom-header","'nguyen-vu")
                .body(iPostService.getAllPosts(pageSize,pageNo,sortBy,sortDir));
    }
}
