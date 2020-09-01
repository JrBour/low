package jrbour.blog.dao;

import jrbour.blog.model.Category;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryDao extends JpaRepository<Category, UUID> {
    @Override
    Optional<Category> findById(UUID uuid);

    @Override
    boolean existsById(UUID uuid);

    @Override
    void deleteById(UUID uuid);
}
