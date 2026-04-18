package com.sujan.fullcrudproductapi.service;

import com.sujan.fullcrudproductapi.dto.CategoryStats;
import com.sujan.fullcrudproductapi.dto.ProductRequestDTO;
import com.sujan.fullcrudproductapi.dto.ProductResponseDTO;
import com.sujan.fullcrudproductapi.exception.DuplicateResourceException;
import com.sujan.fullcrudproductapi.exception.ResourceNotFoundException;
import com.sujan.fullcrudproductapi.mapper.ProductMapper;
import com.sujan.fullcrudproductapi.model.Product;
import com.sujan.fullcrudproductapi.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO){
        if(productRepository.existsByName(productRequestDTO.name())){
            throw new DuplicateResourceException("Product already exists");
        }

        Product saved = productRepository.save(ProductMapper.toModel(productRequestDTO));
        return ProductMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Integer id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return ProductMapper.toDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findAll(int page, int size, String sortBy, String category) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Product> result = (category != null)
                ? productRepository.findByCategoryAndStockGreaterThanOrderByPriceAsc(category, 0, pageable)
                : productRepository.findAll(pageable);

        return result.map(ProductMapper::toDTO);
    }

    public ProductResponseDTO update(Integer id, ProductRequestDTO dto) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        p.setName(dto.name());
        p.setPrice(dto.price());
        p.setStock(dto.stock());
        p.setCategory(dto.category());
        p.setDescription(dto.description());

        return ProductMapper.toDTO(productRepository.save(p));
    }

    public void delete(Integer id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(p);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> search(String keyword, BigDecimal min, BigDecimal max, Pageable pageable) {
        return productRepository.searchProducts(keyword, min, max, pageable).map(ProductMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public CategoryStats getStats(String category) {
        return new CategoryStats(
                category,
                productRepository.getTotalStockValueByCategory(category),
                productRepository.getAveragePriceByCategory(category)
        );
    }
}