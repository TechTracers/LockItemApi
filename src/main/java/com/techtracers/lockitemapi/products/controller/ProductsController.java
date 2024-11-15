package com.techtracers.lockitemapi.products.controller;

import com.crudjpa.controller.CrudController;
import com.crudjpa.exception.ResourceNotFoundException;
import com.techtracers.lockitemapi.products.domain.model.Product;
import com.techtracers.lockitemapi.products.domain.model.ProductCategory;
import com.techtracers.lockitemapi.products.domain.services.IProductCategoryService;
import com.techtracers.lockitemapi.products.domain.services.IProductService;
import com.techtracers.lockitemapi.products.mapping.ProductMapper;
import com.techtracers.lockitemapi.products.resources.CreateProductResource;
import com.techtracers.lockitemapi.products.resources.ProductResource;
import com.techtracers.lockitemapi.products.resources.UpdateProductResource;
import com.techtracers.lockitemapi.shared.exception.InvalidCreateResourceException;
import com.techtracers.lockitemapi.shared.exception.InvalidUpdateResourceException;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${lockitem.products.path}")
public class ProductsController extends CrudController<Product, Long, ProductResource, CreateProductResource, UpdateProductResource> {

    private final IProductService productService;
    private final IProductCategoryService productCategoryService;

    public ProductsController(IProductService productService, ProductMapper mapper, IProductCategoryService productCategoryService) {
        super(productService, mapper);
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }

    @Override
    protected boolean isValidCreateResource(CreateProductResource createProductResource) {
        return true;
    }

    @Override
    protected boolean isValidUpdateResource(UpdateProductResource updateProductResource) {
        return true;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResource> getProductById(@PathVariable Long id) {
        return getById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductResource>> getAllProducts() {
        return getAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResource> createProduct(@Valid @RequestBody CreateProductResource resource, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidCreateResourceException(getErrorsFromResult(result));
        }
        Optional<ProductCategory> category = productCategoryService.getById(resource.getCategoryId());
        if (category.isEmpty())
            throw new InvalidCreateResourceException("The given category does not exist.");
        resource.setCategory(category.get());

        return insert(resource);
    }

    @Override
    protected void fromUpdateResourceToModel(UpdateProductResource updateProductResource, Product product) {
        super.fromUpdateResourceToModel(updateProductResource, product);
        product.getId();
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResource> updateProduct(@PathVariable Long id, @Valid @RequestBody UpdateProductResource resource, BindingResult result) {
        if (result.hasErrors())
            throw new InvalidUpdateResourceException(getErrorsFromResult(result));

        return update(id, resource);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResource> deleteProduct(@PathVariable Long id) {
        return delete(id);
    }

    @GetMapping(value = "search/{name}")
    public ResponseEntity<ProductResource> getByName(@PathVariable String name) {
        Optional<Product> product = productService.findByName(name);
        if (product.isEmpty())
            throw new ResourceNotFoundException("Product with name %s not exists".formatted(name));

        return ResponseEntity.ok(fromModelToResource(product.get()));
    }

    @GetMapping(value = "search/")
    public ResponseEntity<List<ProductResource>> searchProducts(@RequestParam(required = false) @Nullable Long categoryId) {
        Optional<ProductCategory> category = productCategoryService.getById(categoryId);
        if(category.isEmpty())
            throw new ResourceNotFoundException("Category with id %s not exists".formatted(categoryId));

        List<ProductResource> products = productService.findProductsByCategory(category.get())
                .stream()
                .map(this::fromModelToResource)
                .toList();

        return ResponseEntity.ok(products);
    }
}
