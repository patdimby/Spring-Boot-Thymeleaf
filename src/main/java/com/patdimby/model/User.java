package com.patdimby.model;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Getter
    private String email;

    @Getter
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @Getter
    public Collection<Role> roles;

    public User(){

    }

    public User(String firstName, String lastName, String email, String encode, List<Role> roleUser) {
    }

    // Getters and Setters
}