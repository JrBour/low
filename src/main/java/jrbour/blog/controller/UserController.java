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
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping
    public List<User> getUsers(){
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        return this.userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        Role role = this.roleService.findById(user.getRole().getId());
        user.setRole(role);
        user.setPassword(this.passwordEncoder().encode(user.getPassword()));
        User userAdded = this.userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.findById(userAdded.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable int id) {
        this.userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
