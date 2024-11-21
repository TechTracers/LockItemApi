package com.techtracers.lockitemapi.stores.controller;

import com.crudjpa.controller.CrudController;
import com.crudjpa.exception.ResourceNotFoundException;
import com.techtracers.lockitemapi.products.domain.model.Product;
import com.techtracers.lockitemapi.products.domain.services.IProductService;
import com.techtracers.lockitemapi.shared.exception.InvalidCreateResourceException;
import com.techtracers.lockitemapi.shared.exception.InvalidUpdateResourceException;
import com.techtracers.lockitemapi.shared.exception.ResourceAlreadyExistsException;
import com.techtracers.lockitemapi.shared.exception.ValidationException;
import com.techtracers.lockitemapi.stores.domain.model.Store;
import com.techtracers.lockitemapi.stores.domain.model.StoreProduct;
import com.techtracers.lockitemapi.stores.domain.services.IStoreProductService;
import com.techtracers.lockitemapi.stores.domain.services.IStoreService;
import com.techtracers.lockitemapi.stores.mapping.StoreProductMapper;
import com.techtracers.lockitemapi.stores.resources.CreateStoreProductResource;
import com.techtracers.lockitemapi.stores.resources.StoreProductFilters;
import com.techtracers.lockitemapi.stores.resources.StoreProductResource;
import com.techtracers.lockitemapi.stores.resources.UpdateStoreProductResource;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("${lockitem.stores.path}")
public class StoreProductController extends CrudController<StoreProduct, Long, StoreProductResource, CreateStoreProductResource, UpdateStoreProductResource> {
    private final IStoreService storeService;
    private final IProductService productService;
    private final IStoreProductService storeProductService;

    StoreProductController(final IStoreService storeService, final IProductService productService, IStoreProductService storeProductService, StoreProductMapper storeProductMapper) {
        super(storeProductService, storeProductMapper);
        this.storeService = storeService;
        this.productService = productService;
        this.storeProductService = storeProductService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<StoreProductResource>> getAllProducts() {
        return getAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<StoreProductResource> getProductById(@PathVariable Long id) {
        return getById(id);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<StoreProductResource>> getAllProductsByStore(@PathVariable Long id) {
        Store store = getStore(id);
        List<StoreProductResource> products = this.storeProductService.findByStore(store)
                .stream()
                .map(mapper::fromModelToResource)
                .toList();

        return ResponseEntity.ok(products);
    }

    private Product getProduct(StoreProductFilters filters) {
        Optional<Product> product;
        if (Objects.nonNull(filters.getId()))
            product = productService.getById(filters.getId());
        else if (Objects.nonNull(filters.getName()))
            product = productService.findByName(filters.getName());
        else throw new ValidationException("No valid filter given.");
        if (product.isEmpty())
            throw new ResourceNotFoundException("Not found any product with the given filters");
        return product.get();
    }

    private Store getStore(Long id) {
        Optional<Store> store = storeService.getById(id);
        if (store.isEmpty())
            throw new ResourceNotFoundException("The store with the id %s does not exist.".formatted(id));

        return store.get();
    }

    private ImmutablePair<Store, Product> getStoreAndProduct(Long id, Long productId) {
        Store store = getStore(id);
        Product product = getProduct(
                StoreProductFilters.builder()
                        .id(productId)
                        .build()
        );


        Optional<StoreProduct> storeProduct = storeProductService.findByStoreAndProduct(store, product);
        if (storeProduct.isPresent())
            throw new ResourceAlreadyExistsException("The given product already exists for that store.");
        return new ImmutablePair<>(store, product);
    }


    @PostMapping("/{id}/products")
    public ResponseEntity<StoreProductResource> insertProductToStore(@PathVariable Long id, @RequestBody CreateStoreProductResource resource, BindingResult result) {
        if (result.hasErrors())
            throw new InvalidCreateResourceException(getErrorsFromResult(result));

        ImmutablePair<Store, Product> pair = getStoreAndProduct(id, resource.getProductId());
        resource.setStore(pair.getLeft());
        resource.setProduct(pair.getRight());
        return insert(resource);
    }

    @Override
    protected void fromUpdateResourceToModel(UpdateStoreProductResource resource, StoreProduct storeProduct) {
        Long productId = resource.getProductId();
        if (productId != null && productId != storeProduct.getProduct().getId())
            resource.setProduct(
                    getProduct(
                            StoreProductFilters.builder()
                                    .id(productId)
                                    .build()
                    )
            );
        super.fromUpdateResourceToModel(resource, storeProduct);
        Double price = storeProduct.getPrice();
    }

    @PutMapping("/{id}/products")
    public ResponseEntity<StoreProductResource> updateProductFromStore(@PathVariable Long id, @RequestBody UpdateStoreProductResource resource, BindingResult result) {
        if (result.hasErrors())
            throw new InvalidUpdateResourceException(getErrorsFromResult(result));


        return update(id, resource);
    }

    @DeleteMapping("/{id}/products/{productId}")
    public ResponseEntity<StoreProductResource> deleteProductFromStore(@PathVariable Long id, @PathVariable Long productId) {
        ImmutablePair<Store, Product> result = getStoreAndProduct(id, productId);
        Optional<StoreProduct> store = storeProductService.findByStoreAndProduct(result.getLeft(), result.getRight());
        if (store.isEmpty())
            throw new ResourceNotFoundException("The given product does not exist for that store.");

        return delete(store.get().getId());
    }

    @Override
    protected boolean isValidCreateResource(CreateStoreProductResource createStoreProductResource) {
        return true;
    }

    @Override
    protected boolean isValidUpdateResource(UpdateStoreProductResource updateStoreProductResource) {
        return true;
    }
}
