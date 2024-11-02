package com.techtracers.lockitemapi.products.service;

import com.crudjpa.service.impl.CrudService;
import com.techtracers.lockitemapi.products.domain.model.ProductCategory;
import com.techtracers.lockitemapi.products.domain.services.IProductCategoryService;
import com.techtracers.lockitemapi.products.persistence.repository.IProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryService extends CrudService<ProductCategory, Long> implements IProductCategoryService {
    private final IProductCategoryRepository categoryRepository;

    public ProductCategoryService(IProductCategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<ProductCategory> findProductCategoryByName(String name) {
        return categoryRepository.findProductCategoryByName(name);
    }
}
