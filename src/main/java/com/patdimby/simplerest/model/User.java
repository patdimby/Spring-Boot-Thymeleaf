package com.patdimby.simplerest.model;

import java.util.Collection;
import lombok.Data;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;


@Entity
@Data
@AllArgsConstructor
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
	
	@NotBlank(message = "Password cannot be black")
	private String password;
	
	@NotNull(message = "Role is mandatory")
	private UserRole role; // ROLE_USER, ROLE_ADMIN
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
		            name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "role_id", referencedColumnName = "id"))
	
	private Collection<Role> roles;
	
	public User() {
		
	}

	@Override
    public String toString() {
        return "User{" +
               "id='" + id + '\'' +
               ", name='" + firstName + '\'' +
               ", email='" + email + '\'' +
               ", role='" + role + '\'' +
               '}';
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

}
