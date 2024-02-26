package com.ngleanhvu.service.impl;

import com.ngleanhvu.dto.PostDTO;
import com.ngleanhvu.entity.Post;
import com.ngleanhvu.repository.PostRepository;
import com.ngleanhvu.service.IPostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {

    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        postRepository.save(post);
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Integer id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Post doesn't found"));
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO) {
        Post post = postRepository
                .findById(postDTO.getId())
                .orElseThrow(() -> new RuntimeException("Post doesn't not found"));
        changAttribute(post,postDTO);
        return modelMapper.map(post,PostDTO.class);
    }

    @Override
    public void deletePostById(Integer id) {
        postRepository.deleteById(id);
    }

    private void changAttribute(Post previousPost, PostDTO newPost){
        String content= newPost.getContent();
        String description=newPost.getDescription();
        String title=newPost.getTitle();
        if(content!=null) previousPost.setContent(content);
        if(description!=null) previousPost.setDescription(description);
        if(title!=null) previousPost.setTitle(title);
    }
}

