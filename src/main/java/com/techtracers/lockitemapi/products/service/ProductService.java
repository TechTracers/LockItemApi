package com.techtracers.lockitemapi.products.service;

import com.crudjpa.service.impl.CrudService;
import com.techtracers.lockitemapi.products.domain.model.Product;
import com.techtracers.lockitemapi.products.domain.model.ProductCategory;
import com.techtracers.lockitemapi.products.domain.services.IProductService;
import com.techtracers.lockitemapi.products.persistence.repository.IProductRepository;
import com.techtracers.lockitemapi.shared.exception.ResourceAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService extends CrudService<Product, Long> implements IProductService {

    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) throws RuntimeException {
        Optional<Product> duplicated = this.findByNameAndCategory(product.getName(), product.getCategory());
        if((product.getId() == null && duplicated.isPresent()) ||
                (product.getId() != null && duplicated.isPresent() && !product.getId().equals(duplicated.get().getId())))
            throw new ResourceAlreadyExistsException("The given product already exists.");
        return super.save(product);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findProductsByCategory(ProductCategory productCategory) {
        return productRepository.findProductsByCategory(productCategory);
    }

    @Override
    public Optional<Product> findByNameAndCategory(String name, ProductCategory category) {
        return productRepository.findByNameAndCategory(name, category);
    }
}
