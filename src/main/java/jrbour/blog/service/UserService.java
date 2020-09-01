package jrbour.blog.service;

import jrbour.blog.dao.UserDao;
import jrbour.blog.exception.EmailNotFoundException;
import jrbour.blog.exception.NotFoundUUIDException;
import jrbour.blog.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao repository;

    public UserService(UserDao repository) {
        this.repository = repository;
    }

    public User loadUserByEmail(String email) throws EmailNotFoundException {
        User user = this.repository.findByEmail(email);
        if (user == null) {
            throw new EmailNotFoundException(email);
        }
        return user;
    }

    public User findByEmail(String email){
        return this.repository.findByEmail(email);
    }

    public User findById(UUID id){
        return this.repository.findById(id).orElseThrow(() -> new NotFoundUUIDException(id, "user"));
    }

    public User save(User entity){
        return this.repository.save(entity);
    }

    public List<User> findAll(){
        return this.repository.findAll();
    }

    public void deleteById(UUID id){
        if(this.repository.existsById(id))
            this.repository.deleteById(id);
        else
            throw new NotFoundUUIDException(id, "user");
    }
}
