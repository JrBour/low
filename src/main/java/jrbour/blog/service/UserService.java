package jrbour.blog.service;

import jrbour.blog.dao.UserDao;
import jrbour.blog.exception.NotFoundException;
import jrbour.blog.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao repository;

    public UserService(UserDao repository) {
        this.repository = repository;
    }

    public User findById(Integer id){
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException(id, "user"));
    }

    public User save(User entity){
        return this.repository.save(entity);
    }

    public List<User> findAll(){
        return this.repository.findAll();
    }

    public void deleteById(Integer id){
        if(this.repository.existsById(id))
            this.repository.deleteById(id);
        else
            throw new NotFoundException(id, "user");
    }
}
