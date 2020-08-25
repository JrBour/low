package jrbour.blog.service;

import jrbour.blog.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// S = Entity
@Service
public class CrudService<S> {

    @Autowired
    private final CrudRepository<S, Integer> repository;

    public CrudService(CrudRepository<S, Integer> repository) {
        this.repository = repository;
    }

    public S findById(Integer id){
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public S save(S entity){
        return this.repository.save(entity);
    }

    public List<S> findAll(){
        return (List<S>) this.repository.findAll();
    }

    public void deleteById(Integer id){
        if(this.repository.existsById(id))
            this.repository.deleteById(id);
        else
            throw new NotFoundException(id);
    }
}
