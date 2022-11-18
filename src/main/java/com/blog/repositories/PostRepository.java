package com.blog.repositories;

import com.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    Post getPostByUrl(String url);
    @Query("SELECT p from Post p WHERE " + " p.title LIKE CONCAT('%', :query, '%') OR " + " p.shortDescription LIKE CONCAT('%', :query, '%')")
    List<Post> searchPosts(@Param("query") String query);
}

