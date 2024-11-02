package com.techtracers.lockitemapi.stores.mapping;

import com.crudjpa.mapping.IEntityMapper;
import com.techtracers.lockitemapi.shared.mapping.EnhancedModelMapper;
import com.techtracers.lockitemapi.stores.domain.model.Store;
import com.techtracers.lockitemapi.stores.resources.CreateStoreResource;
import com.techtracers.lockitemapi.stores.resources.StoreResource;
import com.techtracers.lockitemapi.stores.resources.UpdateStoreResource;
import org.springframework.beans.factory.annotation.Autowired;

public class StoreMapper implements IEntityMapper<Store, StoreResource, CreateStoreResource, UpdateStoreResource> {
    @Autowired
    EnhancedModelMapper mapper;


    @Override
    public Store fromCreateResourceToModel(CreateStoreResource createStoreResource) {
        return mapper.map(createStoreResource, Store.class);
    }

    @Override
    public void fromCreateResourceToModel(CreateStoreResource createStoreResource, Store store) {
        mapper.map(createStoreResource, store);
    }

    @Override
    public StoreResource fromModelToResource(Store store) {
        return mapper.map(store, StoreResource.class);
    }

    @Override
    public Store fromUpdateResourceToModel(UpdateStoreResource updateStoreResource) {
        return mapper.map(updateStoreResource, Store.class);
    }

    @Override
    public void fromUpdateResourceToModel(UpdateStoreResource updateStoreResource, Store store) {
        mapper.map(updateStoreResource, store);
    }
}
