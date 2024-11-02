package com.techtracers.lockitemapi.products.domain.services;

import com.crudjpa.service.ICrudService;
import com.techtracers.lockitemapi.products.domain.model.ProductCategory;

import java.util.Optional;

public interface IProductCategoryService extends ICrudService<ProductCategory, Long> {
    Optional<ProductCategory> findProductCategoryByName(String name);
}
