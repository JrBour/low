package jrbour.blog.controller;

import jrbour.blog.model.Category;
import jrbour.blog.model.Post;
import jrbour.blog.model.User;
import jrbour.blog.service.CategoryService;
import jrbour.blog.service.PostService;
import jrbour.blog.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Post> getPosts(){
        return this.postService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable UUID id){
        Post post = this.postService.findById(id);

        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@Valid @RequestBody Post post){
        User author = this.userService.findById(post.getAuthor().getId());
        Category category = this.categoryService.findById(post.getCategory().getId());
        post.setAuthor(author);
        post.setCategory(category);
        this.postService.save(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.postService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
