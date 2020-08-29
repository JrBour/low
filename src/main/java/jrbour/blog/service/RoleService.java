package jrbour.blog.service;

import jrbour.blog.dao.RoleDao;
import jrbour.blog.exception.NotFoundException;
import jrbour.blog.model.Role;
import org.springframework.stereotype.Service;


@Service
public class RoleService {

    private final RoleDao repository;

    public RoleService(RoleDao repository) {
        this.repository = repository;
    }

    public Role findById(Integer id){
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException(id, "role"));
    }

    public Role save(Role entity){
        return this.repository.save(entity);
    }
}
