package com.sujan.fullcrudproductapi.controller;

import com.sujan.fullcrudproductapi.dto.CategoryStats;
import com.sujan.fullcrudproductapi.dto.ProductRequestDTO;
import com.sujan.fullcrudproductapi.dto.ProductResponseDTO;
import com.sujan.fullcrudproductapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO created = productService.createProduct(productRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @GetMapping
    public Page<ProductResponseDTO> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false) String category
    ) {
        return productService.findAll(page, size, sortBy, category);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('product:write')")
    public ProductResponseDTO update(@PathVariable Integer id,
                                     @Valid @RequestBody ProductRequestDTO dto) {
        return productService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #username == authentication.name")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public Page<ProductResponseDTO> search(
            @RequestParam String keyword,
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return productService.search(keyword, min, max, PageRequest.of(page, size));
    }

    @GetMapping("/category/{category}/stats")
    public CategoryStats stats(@PathVariable String category) {
        return productService.getStats(category);
    }
}

