package com.techtracers.lockitemapi.stores.domain.services;

import com.crudjpa.service.ICrudService;
import com.techtracers.lockitemapi.products.domain.model.Product;
import com.techtracers.lockitemapi.stores.domain.model.Store;
import com.techtracers.lockitemapi.stores.domain.model.StoreProduct;

import java.util.List;
import java.util.Optional;

public interface IStoreProductService extends ICrudService<StoreProduct, Long> {
    List<StoreProduct> findByStore(Store store);
    Optional<StoreProduct> findByStoreAndProduct(Store store, Product product);
    Optional<StoreProduct> removeByStoreAndProduct(Store store, Product product);
}
