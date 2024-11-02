package com.techtracers.lockitemapi.stores.persistence.repository;

import com.techtracers.lockitemapi.products.domain.model.Product;
import com.techtracers.lockitemapi.stores.domain.model.Store;
import com.techtracers.lockitemapi.stores.domain.model.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IStoreProductRepository extends JpaRepository<StoreProduct, Long> {
    List<StoreProduct> findByStore(Store store);
    Optional<StoreProduct> findByStoreAndProduct(Store store, Product product);
    Optional<StoreProduct> removeByStoreAndProduct(Store store, Product product);
}
