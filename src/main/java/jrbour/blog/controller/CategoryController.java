package jrbour.blog.controller;

import jrbour.blog.model.Category;
import jrbour.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories(){
        return this.categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id){
        Category category = this.categoryService.findById(id);

        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        this.categoryService.save(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Category> editCategory(@PathVariable int id, @RequestBody Category category){
        Category categoryToEdit = this.categoryService.findById(id);
        categoryToEdit.setName(category.getName());
        this.categoryService.save(categoryToEdit);

        return ResponseEntity.ok(categoryToEdit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id){
        this.categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
