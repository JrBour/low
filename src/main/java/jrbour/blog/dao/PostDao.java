package jrbour.blog.dao;

import jrbour.blog.model.Post;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface PostDao extends JpaRepository<Post, UUID> {
}
