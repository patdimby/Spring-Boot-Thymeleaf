package com.patdimby.simplerest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class Event {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "image")
	private String img;
	
	@Column(name = "heading")
	private String heading;
	
	@Column(name = "sub")
	private String sub;

	@Column(name = "paragraph")
	private String paragraph;
	
	@Column(name = "actual")
	private int actual = 0;
	
	@Column(name = "link")
	private String link;
}
