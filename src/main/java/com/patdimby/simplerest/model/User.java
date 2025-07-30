package com.patdimby.simplerest.model;

import java.util.Collection;

import lombok.*;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;


@Entity
@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	@NotBlank(message = "Name is mandatory.")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;


    @Column(name = "name")
	private String username;
	
	@Email(message = "Invalid email address")
    @NotBlank(message = "Email is mandatory")
	private String email;
	
	@NotBlank(message = "Password cannot be blank")
	private String password;

	private String confirmpassword;
	
	@NotNull(message = "Role is mandatory")
	private UserRole role; // ROLE_USER, ROLE_ADMIN

	private Sex sex; // MALE, FEMALE
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
		            name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "role_id", referencedColumnName = "id"))
	
	private Collection<Role> roles;


	@Override
    public String toString() {
        return "User{" +
               "id='" + id + '\'' +
               ", name='" + firstName + '\'' +
               ", email='" + email + '\'' +
               ", role='" + role + '\'' +
               '}';
    }

    public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}


}
