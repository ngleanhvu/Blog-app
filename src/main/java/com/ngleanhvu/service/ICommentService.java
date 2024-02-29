package com.ngleanhvu.service;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ngleanhvu.dto.CommentDTO;

import java.util.List;

public interface ICommentService {
    CommentDTO createComment(Integer postId, CommentDTO commentDTO);
    CommentDTO getCommentById(Integer postId, Integer id);
    List<CommentDTO> getAllCommentsByPostId(Integer postId);
    CommentDTO updateComment(Integer postId,
                             Integer id,
                             CommentDTO commentDTO);
    void deleteCommentById(Integer postId,
                           Integer id);
}
