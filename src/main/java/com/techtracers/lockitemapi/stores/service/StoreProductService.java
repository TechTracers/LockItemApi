package com.techtracers.lockitemapi.stores.service;

import com.crudjpa.service.impl.CrudService;
import com.techtracers.lockitemapi.products.domain.model.Product;
import com.techtracers.lockitemapi.stores.domain.model.Store;
import com.techtracers.lockitemapi.stores.domain.model.StoreProduct;
import com.techtracers.lockitemapi.stores.domain.services.IStoreProductService;
import com.techtracers.lockitemapi.stores.persistence.repository.IStoreProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreProductService extends CrudService<StoreProduct, Long> implements IStoreProductService {
    IStoreProductRepository repository;

    public StoreProductService(IStoreProductRepository repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    public List<StoreProduct> findByStore(Store store) {
        return repository.findByStore(store);
    }

    @Override
    public Optional<StoreProduct> findByStoreAndProduct(Store store, Product product) {
        return repository.findByStoreAndProduct(store, product);
    }

    @Override
    public Optional<StoreProduct> removeByStoreAndProduct(Store store, Product product) {
        return repository.removeByStoreAndProduct(store, product);
    }
}
