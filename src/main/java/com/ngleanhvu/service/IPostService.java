package com.ngleanhvu.service;

import com.ngleanhvu.dto.PostDTO;
import org.springframework.stereotype.Service;

public interface IPostService {
    PostDTO createPost(PostDTO postDTO);
    PostDTO getPostById(Integer id);
    PostDTO updatePost(PostDTO postDTO);
    void deletePostById(Integer id);
}
