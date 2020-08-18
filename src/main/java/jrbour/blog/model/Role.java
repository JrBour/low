package jrbour.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @JsonBackReference
    @OneToMany(cascade=ALL, mappedBy="role", targetEntity=User.class)
    private Set<User> users = new HashSet<>();

    public Role(){}

    public Role(@NotNull String name) {
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(HashSet<User> users) {
        this.users = users;
    }
}
