package jrbour.blog.service;

import jrbour.blog.dao.PostDao;
import jrbour.blog.exception.NotFoundException;
import jrbour.blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostDao repository;

    public Post findById(Integer id){
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void save(Post entity){
        this.repository.save(entity);
    }

    public List<Post> findAll(){
        return this.repository.findAll();
    }

    public void deleteById(Integer id){
        if(this.repository.existsById(id))
            this.repository.deleteById(id);
        else
            throw new NotFoundException(id);
    }
}
