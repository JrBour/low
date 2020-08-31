package jrbour.blog.service;

import jrbour.blog.dao.CategoryDao;
import jrbour.blog.exception.NotFoundException;
import jrbour.blog.exception.NotFoundUUIDException;
import jrbour.blog.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryDao repository;

    public CategoryService(CategoryDao repository) {
        this.repository = repository;
    }

    public Category findById(UUID id){
        return this.repository.findById(id).orElseThrow(() -> new NotFoundUUIDException(id, "category"));
    }

    public Category save(Category entity){
        return this.repository.save(entity);
    }

    public List<Category> findAll(){
        return this.repository.findAll();
    }

    public void deleteById(UUID id){
        if(this.repository.existsById(id))
            this.repository.deleteById(id);
        else
            throw new NotFoundUUIDException(id, "category");
    }
}
