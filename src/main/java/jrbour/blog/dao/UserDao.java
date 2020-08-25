package jrbour.blog.dao;

import jrbour.blog.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Primary
public interface UserDao extends JpaRepository<User, Integer> { }
