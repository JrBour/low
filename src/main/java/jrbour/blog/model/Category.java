package jrbour.blog.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="categories")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;

    @NotNull
    @Column(unique=true)
    private String name;

    // Lazy fetch = on demand
    // Eager fetch = Load with the rest of the fields
    @OneToMany(cascade=ALL, mappedBy="category", targetEntity=Post.class)
    private Set<Post> posts = new HashSet<Post>();

    public Category(){}

    public Category(@NotNull String name) {
        this.name = name;
    }

    public int getId() {
        return id;
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
