package com.ngleanhvu.service.impl;

import com.ngleanhvu.dto.PostDTO;
import com.ngleanhvu.entity.Category;
import com.ngleanhvu.entity.Post;
import com.ngleanhvu.entity.User;
import com.ngleanhvu.execption.ResourceNotFoundException;
import com.ngleanhvu.repository.CategoryRepository;
import com.ngleanhvu.repository.PostRepository;
import com.ngleanhvu.repository.UserRepository;
import com.ngleanhvu.service.IPostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository,
                           ModelMapper modelMapper,
                           CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        Category category = categoryRepository.findById(postDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDTO.getCategoryId().toString()));
        post.setCategory(category);
        postRepository.save(post);
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Integer id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO) {
        Category category = categoryRepository.findById(postDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDTO.getCategoryId().toString()));
        Post post = postRepository
                .findById(postDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postDTO.getId().toString()
                ));
        changAttribute(category, post, postDTO);
        postRepository.save(post);
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public void deletePostById(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDTO> getAllPosts(Integer pageSize, Integer pageNo, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy) : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return postRepository
                .findAll(pageable)
                .stream()
                .map(u -> modelMapper.map(u, PostDTO.class))
                .collect(Collectors.toList());
    }

    private void changAttribute(Category category, Post previousPost, PostDTO newPost) {
        String content = newPost.getContent();
        String description = newPost.getDescription();
        String title = newPost.getTitle();
        if (content != null) previousPost.setContent(content);
        if (description != null) previousPost.setDescription(description);
        if (title != null) previousPost.setTitle(title);
        previousPost.setCategory(category);
    }

}

