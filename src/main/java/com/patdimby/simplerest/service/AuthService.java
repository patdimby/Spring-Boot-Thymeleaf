package com.patdimby.simplerest.service;

import com.patdimby.simplerest.dto.AuthRequest;
import com.patdimby.simplerest.dto.AuthResponse;
import com.patdimby.simplerest.dto.RegisterRequest;
import com.patdimby.simplerest.model.User;
import com.patdimby.simplerest.model.UserRole;
import com.patdimby.simplerest.repository.UserRepository;
import com.patdimby.simplerest.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(@Valid RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        UserRole userRole;
        if (request.getRole() == null || request.getRole().isBlank()) {
            userRole = UserRole.ROLE_USER;
        } else {
            try {
                userRole = UserRole.valueOf(request.getRole().toUpperCase());
                if (userRole != UserRole.ROLE_USER && userRole != UserRole.ROLE_ADMIN) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException ex) {
                throw new RuntimeException("Rôle invalide : uniquement ROLE_USER ou ROLE_ADMIN");
            }
        }

        User user = new User();
        user.setUsername(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.ROLE_USER);

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(@Valid AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    // ✅ Méthode utilisée pour récupérer l’utilisateur courant
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }
}
