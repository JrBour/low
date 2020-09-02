package jrbour.blog.controller;

import jrbour.blog.model.JwtRequest;
import jrbour.blog.model.JwtResponse;
import jrbour.blog.model.Role;
import jrbour.blog.model.User;
import jrbour.blog.service.RoleService;
import jrbour.blog.service.UserService;
import jrbour.blog.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Configuration
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping
    public List<User> getUsers(){
        return this.userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable UUID id){
        return this.userService.findById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody JwtRequest jwtRequest) throws Exception{
        this.authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch(DisabledException ex){
            throw new Exception("DISABLED_USER", ex);
        } catch(AuthenticationException ex){
            System.out.println(email);
            throw new Exception("INVALID_CREDENTIALS", ex);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        Role role = this.roleService.findById(user.getRole().getId());
        user.setRole(role);
        user.setPassword(this.bCryptPasswordEncoder().encode(user.getPassword()));
        User userAdded = this.userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.findById(userAdded.getId()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> remove(@PathVariable UUID id) {
        this.userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
