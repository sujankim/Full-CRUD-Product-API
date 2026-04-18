package com.sujan.fullcrudproductapi.repository;

import com.sujan.fullcrudproductapi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Derived: check if product name exists
    boolean existsByName(String name);

    // Derived method: find in-stock products by category ordered by price
    Page<Product> findByCategoryAndStockGreaterThanOrderByPriceAsc(String category, Integer stock, Pageable pageable);

    // JPQL: search by name keyword + price range
    @Query("SELECT p FROM Product p WHERE "+
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))"+
            "AND (:minPrice IS NULL OR p.price >= :minPrice)" +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice)"
    Page<Product> searchProducts(@Param("keyword") String keyword,
                                 @Param("minPrice")BigDecimal minPrice,
                                 @Param("maxPrice") BigDecimal maxPrice,
                                 Pageable pageable);

    // JPQL: SUM(price * stock) for a category
    @Query("SELECT SUM (p.price * p.stock) from Product p where p.category = :category")
    BigDecimal getTotalStockValueByCategory(@Param("category") String category);

    // JPQL: AVG(price) for a category
    @Query("SELECT AVG(p.price) FROM Product p WHERE p.category = :category")
    BigDecimal getAveragePriceByCategory(@Param("category") String category);
}
