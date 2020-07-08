package jrbour.blog.controller;

import jrbour.blog.dao.RoleDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    private RoleDao roleDao;

    public RoleController(RoleDao roleDao){
        this.roleDao = roleDao;
    }

    @GetMapping("/roles")
    public ResponseEntity index(){

    }
}
