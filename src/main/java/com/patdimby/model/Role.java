package com.patdimby.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
        //
    }

    // Getters and Setters
}