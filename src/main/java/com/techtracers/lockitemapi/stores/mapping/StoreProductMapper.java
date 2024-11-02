package com.techtracers.lockitemapi.stores.mapping;

import com.crudjpa.mapping.IEntityMapper;
import com.techtracers.lockitemapi.shared.mapping.EnhancedModelMapper;
import com.techtracers.lockitemapi.stores.domain.model.StoreProduct;
import com.techtracers.lockitemapi.stores.resources.CreateStoreProductResource;
import com.techtracers.lockitemapi.stores.resources.StoreProductResource;
import com.techtracers.lockitemapi.stores.resources.UpdateStoreProductResource;
import org.springframework.beans.factory.annotation.Autowired;

public class StoreProductMapper implements IEntityMapper<StoreProduct, StoreProductResource, CreateStoreProductResource, UpdateStoreProductResource> {
    @Autowired
    EnhancedModelMapper mapper;


    @Override
    public StoreProduct fromCreateResourceToModel(CreateStoreProductResource createStoreProductResource) {
        return mapper.map(createStoreProductResource, StoreProduct.class);
    }

    @Override
    public void fromCreateResourceToModel(CreateStoreProductResource createStoreProductResource, StoreProduct storeProduct) {
        mapper.map(storeProduct, createStoreProductResource);
    }

    @Override
    public StoreProductResource fromModelToResource(StoreProduct storeProduct) {
        return mapper.map(storeProduct, StoreProductResource.class);
    }

    @Override
    public StoreProduct fromUpdateResourceToModel(UpdateStoreProductResource updateStoreProductResource) {
        return mapper.map(updateStoreProductResource, StoreProduct.class);
    }

    @Override
    public void fromUpdateResourceToModel(UpdateStoreProductResource updateStoreProductResource, StoreProduct storeProduct) {
        mapper.map(updateStoreProductResource, storeProduct);
    }
}
