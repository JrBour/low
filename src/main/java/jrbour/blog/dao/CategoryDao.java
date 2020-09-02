package jrbour.blog.dao;

import jrbour.blog.model.Category;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface CategoryDao extends JpaRepository<Category, UUID> {
}
