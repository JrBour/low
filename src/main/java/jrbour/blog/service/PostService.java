package jrbour.blog.service;

import jrbour.blog.dao.PostDao;
import jrbour.blog.exception.NotFoundUUIDException;
import jrbour.blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostDao repository;

    public Post findById(UUID id){
        return this.repository.findById(id).orElseThrow(() -> new NotFoundUUIDException(id, "post"));
    }

    public void save(Post entity){
        this.repository.save(entity);
    }

    public List<Post> findAll(){
        return this.repository.findAll();
    }

    public void deleteById(UUID id){
        if(this.repository.existsById(id))
            this.repository.deleteById(id);
        else
            throw new NotFoundUUIDException(id, "post");
    }
}
