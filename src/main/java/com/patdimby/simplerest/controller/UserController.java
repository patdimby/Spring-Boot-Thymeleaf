package com.patdimby.simplerest.controller;

import com.patdimby.simplerest.model.User;
import com.patdimby.simplerest.service.AuthService;
import com.patdimby.simplerest.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    // 🔸 Create an user
    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
        try {
            User saved = userService.save(user);
            return ResponseEntity.ok("✅ User created successfully: " + saved.getEmail());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error : " + e.getMessage());
        }
    }

    // 🔸 Get all users.
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    // 🔸 Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("❌ Cannot find user by ID : " + id));
    }

    // 🔸 Update an user
    @PutMapping
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
        try {
            User currentUser = authService.getCurrentUser();
            User updated = userService.updateUser(user, currentUser);
            return ResponseEntity.ok(updated);

        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error : " + e.getMessage());
        }
    }

    // 🔸 Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        try {
            User currentUser = authService.getCurrentUser();
            userService.deleteById(id, currentUser);
            return ResponseEntity.ok("🗑️ User deleted successfully (ID : " + id + ")");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("❌ " + e.getMessage());
        }
    }

    // 🔸 Delete all users
    @DeleteMapping
    public ResponseEntity<String> deleteAllUsers() {
        try {
            User currentUser = authService.getCurrentUser();
            userService.deleteAllUsers(currentUser);
            return ResponseEntity.ok("🧹 All users deleted by an administrator.");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body("🚫 Access denied :only administrators can delete all users..");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("💥 Internal error : " + e.getMessage());
        }
    }

    // 🔸 Get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("❌ Cannot find user with this email : " + email));
    }
}
