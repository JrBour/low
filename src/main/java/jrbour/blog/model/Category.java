package jrbour.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @JsonBackReference
    @OneToMany(cascade=ALL, mappedBy="category", targetEntity=Post.class)
    private Set<Post> posts = new HashSet<Post>();

    public Category(){}

    public Category(@NotNull String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(HashSet<Post> posts) {
        this.posts = posts;
    }
}
