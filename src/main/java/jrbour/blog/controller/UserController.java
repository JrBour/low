package jrbour.blog.controller;

import jrbour.blog.dao.RoleDao;
import jrbour.blog.dao.UserDao;
import jrbour.blog.model.Role;
import jrbour.blog.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Configuration
@RestController
public class UserController {

    private final UserDao userDao;
    private final RoleDao roleDao;

    public UserController(UserDao userDao, RoleDao roleDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("/users")
    public List<User> getAll(){
        return this.userDao.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> one(@PathVariable int id){
        return this.userDao.findById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Optional<User>> addUser(@RequestBody User user){
        Optional<Role> role = this.roleDao.findById(user.getRole().getId());
        user.setRole(role.stream().findFirst().get());
        user.setPassword(this.passwordEncoder().encode(user.getPassword()));
        User userAdded = this.userDao.save(user);

        return ResponseEntity.status(201).body(this.userDao.findById(userAdded.getId()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> remove(@PathVariable int id) {
        //Handle error if the id doesn't exist
        this.userDao.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
