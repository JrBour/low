package jrbour.blog.controller;

import jrbour.blog.model.Role;
import jrbour.blog.model.User;
import jrbour.blog.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Configuration
@RestController
public class UserController {

    private final CrudService<User> userDao;

    private final CrudService<Role> roleDao;

    public UserController(CrudService<User> userDao, CrudService<Role> roleDao){
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
    public User one(@PathVariable int id){
        return this.userDao.findById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        Role role = this.roleDao.findById(user.getRole().getId());
        user.setRole(role);
        user.setPassword(this.passwordEncoder().encode(user.getPassword()));
        User userAdded = this.userDao.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userDao.findById(userAdded.getId()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> remove(@PathVariable int id) {
        //Handle error if the id doesn't exist
        this.userDao.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
