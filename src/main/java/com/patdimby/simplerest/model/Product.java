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

    public void setUserId(Long id){
        this.userId = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    private Long userId;
}
