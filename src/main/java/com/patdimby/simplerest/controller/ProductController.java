package com.patdimby.simplerest.controller;

import com.patdimby.simplerest.exception.ResourceNotFoundException;
import com.patdimby.simplerest.model.Product;
import com.patdimby.simplerest.model.User;
import com.patdimby.simplerest.repository.UserRepository;
import com.patdimby.simplerest.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserRepository userRepository;

    // 🔸 Create product
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> createProduct(@Valid @RequestBody Product product, Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        product.setUserId(user.getId());
        productService.createProduct(product);
        return ResponseEntity.ok("✅ Product successfully created.");
    }

    // 🔸 Get products.
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // 🔸 All products.
    @GetMapping("/my")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<Product>> getMyProducts() {
        return ResponseEntity.ok(productService.getProductsForCurrentUser());
    }

    // 🔸 Get product by ID.
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductByIdWithOwnershipCheck(id);
            return ResponseEntity.ok(product);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body("❌ Product find with ID : " + id);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body("🚫 Access denied : Forbidden.");
        }
    }

    // 🔸 Update product
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        try {
            productService.updateProductWithOwnershipCheck(id, product);
            return ResponseEntity.ok("✅ Produit mis à jour avec succès.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body("❌ Produit introuvable avec l'ID : " + id);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body("🚫 Accès refusé : vous ne pouvez pas modifier ce produit.");
        }
    }

    // 🔸 Delete a product.
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProductWithOwnershipCheck(id);
            return ResponseEntity.ok("🗑️ Product deleted successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body("❌ Cannot find product with ID : " + id);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body("🚫 Forbidden : you cannot delete this product.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("💥 Internal error : " + e.getMessage());
        }
    }

    // 🔸 Delete all products (ADMIN only)
    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> deleteAllProducts() {
        try {
            productService.deleteAllProducts();
            return ResponseEntity.ok("🧹 All products are deleted by an administrator.");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body("🚫 Access denied : only administrators can delete all products");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("💥 Internal error : " + e.getMessage());
        }
    }
}
