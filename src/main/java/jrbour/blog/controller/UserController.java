package jrbour.blog.controller;

import jrbour.blog.dao.RoleDao;
import jrbour.blog.dao.UserDao;
import jrbour.blog.model.Role;
import jrbour.blog.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserDao userDao;
    private final RoleDao roleDao;

    public UserController(UserDao userDao, RoleDao roleDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @GetMapping("/users")
    public List<User> all(){
        return this.userDao.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> one(@PathVariable int id){
        return this.userDao.findById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody User user){
        Optional<Role> role = this.roleDao.findById(user.getRole().getId());
//        User userAdded = this.userDao.save(user);
//
//        if (userAdded == null)
//            return ResponseEntity.status(400).build();

        return ResponseEntity.status(204).body("cc");
    }
}
