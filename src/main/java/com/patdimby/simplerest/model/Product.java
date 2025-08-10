package com.patdimby.simplerest.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    private String name;

    private String description;

    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price cannot be less than zero")
    private Double price;

    private Long userId;
}
