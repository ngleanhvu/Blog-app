package com.ngleanhvu.service;

import com.ngleanhvu.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPostService {
    PostDTO createPost(PostDTO postDTO);
    PostDTO getPostById(Integer id);
    PostDTO updatePost(PostDTO postDTO);
    void deletePostById(Integer id);
    List<PostDTO> getAllPosts(Integer pageSize, Integer pageNo, String sortBy, String sortDir);
}
