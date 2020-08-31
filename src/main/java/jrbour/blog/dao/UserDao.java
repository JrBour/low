package jrbour.blog.dao;

import jrbour.blog.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Primary
public interface UserDao extends JpaRepository<User, UUID> {
    @Override
    Optional <User> findById(UUID id);

    @Override
    void deleteById(UUID integer);

    @Override
    boolean existsById(UUID integer);
}
