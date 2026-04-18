package com.sujan.fullcrudproductapi.mapper;

import com.sujan.fullcrudproductapi.dto.ProductRequestDTO;
import com.sujan.fullcrudproductapi.dto.ProductResponseDTO;
import com.sujan.fullcrudproductapi.model.Product;

public class ProductMapper {

    public static ProductResponseDTO toDTO(Product product){
        ProductResponseDTO productDTO = new ProductResponseDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(product.getCategory());
        productDTO.setDescription(product.getDescription());
        productDTO.setStock(product.getStock());
        return productDTO;
    }

    public static Product toModel(ProductRequestDTO productResponseDTO){
        Product product = new Product();
        product.setName(productResponseDTO.name());
        product.setPrice(productResponseDTO.price());
        product.setCategory(productResponseDTO.category());
        product.setDescription(productResponseDTO.description());
        product.setStock(productResponseDTO.stock());
        return product;
    }
}
