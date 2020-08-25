package jrbour.blog.dao;

import jrbour.blog.model.Role;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public interface RoleDao extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
