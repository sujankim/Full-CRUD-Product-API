package com.sujan.fullcrudproductapi.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequestDTO(

        @NotBlank(message = "Name is Required")
        @Size(max=20, message = "Enter full name")
        String name,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be greater than zero")
        BigDecimal price,

        @NotNull(message = "Stock is required")
        @Min(value = 0, message = "Stock cannot be negative")
        Integer stock,

        @NotBlank(message = "Category is required")
        @Pattern(regexp = "^(ELECTRONICS|CLOTHING|FOOD|BOOKS)$",
                message = "Category must be one of: ELECTRONICS, CLOTHING, FOOD, BOOKS")
        String category,

        @Size(max = 500, message = "Description cannot exceed 500 characters")
        String description

) {
}
