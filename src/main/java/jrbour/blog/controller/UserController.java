package jrbour.blog.controller;

import jrbour.blog.dao.UserDao;
import jrbour.blog.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao){
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public List<User> all(){
        return this.userDao.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> one(@PathVariable int id){
        return this.userDao.findById(id);
    }

    @PostMapping("/movies")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User userAdded = this.userDao.save(user);

        if (userAdded == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.status(201).body(userAdded);
    }
}
