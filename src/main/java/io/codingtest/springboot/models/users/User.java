package io.codingtest.springboot.models.users;

import io.codingtest.springboot.models.users.Role;

import javax.persistence.*;

import java.util.Collection;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

    public User(String username, String password, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    @Column(length = 50,nullable = false)
    public String getUsername() {
        return username;
    }
    @Column(length = 120,nullable = false)
    public String getPassword() {
        return password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
