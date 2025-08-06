package com.patdimby.simplerest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;   

    @Column(name = "tag")
    private String tag;

    @Column(name = "created")
    private LocalDateTime createdOn;

    @Column(name = "image")
    private String img;
	
	@Column(name = "author")
    private String author;
	
	@Column(name = "paragraph")
    private String paragraph;
}
