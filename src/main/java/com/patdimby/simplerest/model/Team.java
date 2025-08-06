package com.patdimby.simplerest.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "heading")
    private String heading;   

    @Column(name = "designation")
    private String designation;

    @Column(name = "email")
    private String email;
	
    @Column(name = "image")
    private String img;	
}
