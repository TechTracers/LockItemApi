package com.techtracers.lockitemapi.products.mapping;

import com.crudjpa.mapping.IEntityMapper;
import com.techtracers.lockitemapi.products.domain.model.ProductCategory;
import com.techtracers.lockitemapi.products.resources.CreateProductCategoryResource;
import com.techtracers.lockitemapi.products.resources.ProductCategoryResource;
import com.techtracers.lockitemapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductCategoryMapper implements IEntityMapper<ProductCategory, ProductCategoryResource, CreateProductCategoryResource, CreateProductCategoryResource> {
    @Autowired
    EnhancedModelMapper mapper;

    @Override
    public ProductCategory fromCreateResourceToModel(CreateProductCategoryResource createProductCategoryResource) {
        return mapper.map(createProductCategoryResource, ProductCategory.class);
    }

    @Override
    public void fromCreateResourceToModel(CreateProductCategoryResource createProductCategoryResource, ProductCategory productCategory) {
        mapper.map(createProductCategoryResource, productCategory);
    }

    @Override
    public ProductCategoryResource fromModelToResource(ProductCategory productCategory) {
        return mapper.map(productCategory, ProductCategoryResource.class);
    }

    @Override
    public ProductCategory fromUpdateResourceToModel(CreateProductCategoryResource createProductCategoryResource) {
        return mapper.map(createProductCategoryResource, ProductCategory.class);
    }

    @Override
    public void fromUpdateResourceToModel(CreateProductCategoryResource createProductCategoryResource, ProductCategory productCategory) {
        mapper.map(createProductCategoryResource, productCategory);
    }
}
