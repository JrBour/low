package jrbour.blog.controller;

import jrbour.blog.model.Role;
import jrbour.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles/{id}")
    public Role one (@PathVariable int id){ return this.roleService.findById(id); }

    @PostMapping("/roles")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        Role roleAdded = this.roleService.save(role);

        return ResponseEntity.status(HttpStatus.CREATED).body(roleAdded);
    }
}
