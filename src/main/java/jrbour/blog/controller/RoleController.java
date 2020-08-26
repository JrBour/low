package jrbour.blog.controller;

import jrbour.blog.model.Role;
import jrbour.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/{id}")
    public Role getRole(@PathVariable int id){ return this.roleService.findById(id); }
}
