package jrbour.blog.controller;

import jrbour.blog.model.Post;
import jrbour.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getPosts(){
        return this.postService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable int id){
        Post post = this.postService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        this.postService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
