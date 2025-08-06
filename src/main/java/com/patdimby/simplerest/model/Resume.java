package com.patdimby.simplerest.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "heading")
    private String heading;

    @Column(name = "designation")
    private String designation;

    @Column(name = "link")
    private String link;
}
