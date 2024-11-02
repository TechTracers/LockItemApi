package com.techtracers.lockitemapi.stores.controller;

import com.crudjpa.controller.CrudController;
import com.techtracers.lockitemapi.products.domain.services.IProductService;
import com.techtracers.lockitemapi.products.mapping.ProductMapper;
import com.techtracers.lockitemapi.shared.exception.InvalidCreateResourceException;
import com.techtracers.lockitemapi.shared.exception.InvalidUpdateResourceException;
import com.techtracers.lockitemapi.stores.domain.model.Store;
import com.techtracers.lockitemapi.stores.domain.services.IStoreService;
import com.techtracers.lockitemapi.stores.mapping.StoreMapper;
import com.techtracers.lockitemapi.stores.resources.CreateStoreResource;
import com.techtracers.lockitemapi.stores.resources.StoreResource;
import com.techtracers.lockitemapi.stores.resources.UpdateStoreResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${lockitem.stores.path}")
public class StoreController extends CrudController<Store, Long, StoreResource, CreateStoreResource, UpdateStoreResource> {


    protected StoreController(IStoreService service, StoreMapper mapper, ProductMapper productMapper, IProductService productService) {
        super(service, mapper);
    }

    @Override
    protected boolean isValidCreateResource(CreateStoreResource createStoreResource) {
        return true;
    }

    @Override
    protected boolean isValidUpdateResource(UpdateStoreResource updateStoreResource) {
        return true;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreResource> getStoreById(@PathVariable Long id) {
        return getById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StoreResource>> getAllStores() {
        return getAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreResource> createStore(@RequestBody CreateStoreResource resource, BindingResult result) {
        if (result.hasErrors())
            throw new InvalidCreateResourceException(getErrorsFromResult(result));
        return insert(resource);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreResource> updateStore(@PathVariable Long id, @RequestBody UpdateStoreResource resource, BindingResult result) {
        if (result.hasErrors())
            throw new InvalidUpdateResourceException(getErrorsFromResult(result));
        return update(id, resource);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreResource> deleteStore(@PathVariable Long id) {
        return delete(id);
    }


}
