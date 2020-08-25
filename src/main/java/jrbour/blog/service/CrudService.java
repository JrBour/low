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
    private CrudRepository<S, Integer> repository;

    public S findById(Integer id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public S save(S entity){
        return repository.save(entity);
    }

    public List<S> findAll(){
        return (List<S>) repository.findAll();
    }

    public void deleteById(Integer id){
//        if(repository.existsById(id))
            repository.deleteById(id);
//        else
//            throw new NotFoundException(id);
    }
}
