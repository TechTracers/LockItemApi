package com.techtracers.lockitemapi.products.persistence.repository;


import com.techtracers.lockitemapi.products.domain.model.Product;
import com.techtracers.lockitemapi.products.domain.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String productName);

    List<Product> findProductsByCategory(ProductCategory productCategory);

    Optional<Product> findByNameAndCategory(String name, ProductCategory category);
}
