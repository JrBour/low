package jrbour.blog.dao;

import jrbour.blog.model.Post;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostDao extends JpaRepository<Post, Integer> {
    List<Post> findAll();
    Optional<Post> findById(Integer integer);
    Post save(Post post);
}
