package com.ngleanhvu.repository;

import com.ngleanhvu.dto.PostDTO;
import com.ngleanhvu.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT p FROM Post p WHERE " +
            "p.title LIKE CONCAT('%',:query,'%') " +
            "OR p.description LIKE CONCAT('%',:query,'%') " +
            "OR p.content LIKE CONCAT('%',:query,'%')")
    List<Post> searchProducts(String query);

    @Query(value = "SELECT * FROM posts p WHERE " +
            "p.title LIKE CONCAT('%',:query,'%') " +
            "OR p.description LIKE CONCAT('%',:query,'%') " +
            "OR p.content LIKE CONCAT('%',:query,'%')", nativeQuery = true)
    List<Post> searchProductsSQL(String query);

//    @Override
//    @Transactional(timeout = 10)
//    void deleteById(Integer id);
//
//    @Override
//    @Transactional(propagation = Propagation.NEVER)
//    <S extends Post> S save(S entity);
}
