package jrbour.blog.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    private ArrayList<Post> posts = new ArrayList<Post>();

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

    @OneToMany(cascade=ALL, mappedBy="category")
    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}
