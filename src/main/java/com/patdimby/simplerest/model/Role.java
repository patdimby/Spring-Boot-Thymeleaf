package com.patdimby.simplerest.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Role() {

    }

    public Role(String name) {
        super();
        this.name = name;
    }

}
