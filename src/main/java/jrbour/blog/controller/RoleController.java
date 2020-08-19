package jrbour.blog.controller;

import jrbour.blog.dao.RoleDao;
import jrbour.blog.model.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RoleController {

    private RoleDao roleDao;

    public RoleController(RoleDao roleDao){
        this.roleDao = roleDao;
    }

    @GetMapping("/roles/{id}")
    public Optional<Role> one (@PathVariable int id){ return this.roleDao.findById(id); }

    @PostMapping("/roles")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        Role roleAdded = this.roleDao.save(role);

        if(roleAdded == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.status(HttpStatus.CREATED).body(roleAdded);
    }
}
