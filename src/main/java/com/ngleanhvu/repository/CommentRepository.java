package com.ngleanhvu.repository;

import com.ngleanhvu.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findAllCommentsByPostId(Integer postId);
}
