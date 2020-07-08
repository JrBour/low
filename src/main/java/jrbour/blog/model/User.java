package jrbour.blog.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Email
    @Column(length=254)
    private String email;

    @NotNull
    private String password;

    private Role role;

    public User(){}

    public User(String email, String password, Role role){
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne
    @JoinColumn(name="role_id", nullable=false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
