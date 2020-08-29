package jrbour.blog.service;

import jrbour.blog.dao.CategoryDao;
import jrbour.blog.exception.NotFoundException;
import jrbour.blog.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryDao repository;

    public CategoryService(CategoryDao repository) {
        this.repository = repository;
    }

    public Category findById(Integer id){
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException(id, "category"));
    }

    public Category save(Category entity){
        return this.repository.save(entity);
    }

    public List<Category> findAll(){
        return this.repository.findAll();
    }

    public void deleteById(Integer id){
        if(this.repository.existsById(id))
            this.repository.deleteById(id);
        else
            throw new NotFoundException(id, "category");
    }
}
