package com.techtracers.lockitemapi.products.domain.services;

import com.crudjpa.service.ICrudService;
import com.techtracers.lockitemapi.products.domain.model.Product;
import com.techtracers.lockitemapi.products.domain.model.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface IProductService extends ICrudService<Product, Long> {
    Optional<Product> findByName(String name);

    List<Product> findProductsByCategory(ProductCategory productCategory);

    Optional<Product> findByNameAndCategory(String name, ProductCategory category);

}
