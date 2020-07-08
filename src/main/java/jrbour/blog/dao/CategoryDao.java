package jrbour.blog.dao;

import jrbour.blog.model.Category;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
    List<Category> findAll();
    Optional<Category> findById(Integer integer);
    Category save(Category category);
}
