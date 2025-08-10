package com.patdimby.simplerest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, length = 1024)
    private String email;


}
