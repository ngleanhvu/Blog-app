package com.ngleanhvu.service.impl;

import com.ngleanhvu.dto.CommentDTO;
import com.ngleanhvu.entity.Comment;
import com.ngleanhvu.entity.Post;
import com.ngleanhvu.execption.BlogAPIException;
import com.ngleanhvu.execption.ResourceNotFoundException;
import com.ngleanhvu.repository.CommentRepository;
import com.ngleanhvu.repository.PostRepository;
import com.ngleanhvu.service.ICommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;
    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository=postRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public CommentDTO createComment(Integer postId, CommentDTO commentDTO) {
        Post post=postRepository
                .findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",postId.toString()));
        Comment comment = modelMapper.map(commentDTO,Comment.class);
        comment.setPost(post);
        commentRepository.save(comment);
        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public CommentDTO getCommentById(Integer postId, Integer id) {
        Post post=postRepository
                .findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",postId.toString()));
        Comment comment=commentRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Comment","comment",id.toString()));
        if(!comment.getPost().getId().equals(postId)) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return modelMapper.map(comment,CommentDTO.class);
    }

    @Override
    public List<CommentDTO> getAllCommentsByPostId(Integer postId) {
        return commentRepository
                .findAllCommentsByPostId(postId)
                .stream()
                .map(u->modelMapper.map(u, CommentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO updateComment(Integer postId,
                                    Integer id,
                                    CommentDTO commentDTO) {
        Post post=postRepository
                .findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",postId.toString()));
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id.toString()));
        if(!comment.getPost().getId().equals(postId)){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        update(comment,commentDTO);
        commentRepository.save(comment);
        return modelMapper.map(comment,CommentDTO.class);
    }

    @Override
    public void deleteCommentById(Integer postId, Integer id) {
        Post post=postRepository
                .findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",postId.toString()));
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id.toString()));
        if(!comment.getPost().getId().equals(postId)){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        commentRepository.delete(comment);
    }

    private void update(Comment previousComment, CommentDTO newCommentDTO){
        String name=newCommentDTO.getName();
        String body=newCommentDTO.getBody();
        String email=newCommentDTO.getEmail();
        if(name != null) previousComment.setName(name);
        if(body != null) previousComment.setBody(body);
        if(email != null) previousComment.setEmail(email);
    }
}
