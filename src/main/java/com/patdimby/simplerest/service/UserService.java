package com.patdimby.simplerest.service;

import com.patdimby.simplerest.exception.ResourceAlreadyExistsException;
import com.patdimby.simplerest.exception.ResourceNotFoundException;
import com.patdimby.simplerest.model.User;
import com.patdimby.simplerest.model.UserRole;
import com.patdimby.simplerest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public User save(User user) {
		userRepository.findByEmail(user.getEmail()).ifPresent(existing -> {
			throw new ResourceAlreadyExistsException("User with same email already regitered.");
		});

		if (!user.getPassword().startsWith("$2a$")) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		// Default = USER
		if (user.getRole() == null) {
			user.setRole(UserRole.ROLE_USER);
		}

		return userRepository.save(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public User getById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find user with ID : " + id));
	}

	public void deleteById(Long id, User currentUser) {
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("Impossible : Cannot find user with ID : " + id);
		}

		if (currentUser.getRole() != UserRole.ROLE_ADMIN) {
			throw new AccessDeniedException(
					"🚫 Access denied : only administrator can delete an user.");
		}

		userRepository.deleteById(id);
	}

	public User updateUser(User updatedUser, User currentUser) {
		User existing = userRepository.findById(updatedUser.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User to update not found."));

		if (currentUser.getRole() != UserRole.ROLE_ADMIN) {
			throw new AccessDeniedException(
					"🚫 Access denied : only administrator can update an user.");
		}

		// 🔁 Update fields.
		existing.setUsername(updatedUser.getUsername());
		existing.setEmail(updatedUser.getEmail());
		existing.setRole(updatedUser.getRole());

		if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
			// If password is already encrypted, no need encryption
			if (!updatedUser.getPassword().startsWith("$2a$")) {
				existing.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
			} else {
				existing.setPassword(updatedUser.getPassword());
			}
		}

		return userRepository.save(existing);
	}

	public void deleteAllUsers(User currentUser) {
		if (currentUser.getRole() != UserRole.ROLE_ADMIN) {
			throw new AccessDeniedException("Access denied : only administrator can delete users.");
		}

		userRepository.deleteAll();
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
