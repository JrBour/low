package jrbour.blog.controller;

import jrbour.blog.model.Role;
import jrbour.blog.model.User;
import jrbour.blog.service.RoleService;
import jrbour.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Configuration
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("/users")
    public List<User> getAll(){
        return this.userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User one(@PathVariable int id){
        return this.userService.findById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        Role role = this.roleService.findById(user.getRole().getId());
        user.setRole(role);
        user.setPassword(this.passwordEncoder().encode(user.getPassword()));
        User userAdded = this.userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.findById(userAdded.getId()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> remove(@PathVariable int id) {
        //Handle error if the id doesn't exist
        this.userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
