package com.techtracers.lockitemapi.products.controller;

import com.crudjpa.controller.CrudController;
import com.techtracers.lockitemapi.products.domain.model.ProductCategory;
import com.techtracers.lockitemapi.products.domain.services.IProductCategoryService;
import com.techtracers.lockitemapi.products.mapping.ProductCategoryMapper;
import com.techtracers.lockitemapi.products.resources.CreateProductCategoryResource;
import com.techtracers.lockitemapi.products.resources.ProductCategoryResource;
import com.techtracers.lockitemapi.shared.exception.InvalidCreateResourceException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${lockitem.products.category.path}")
public class ProductCategoriesController extends CrudController<ProductCategory, Long,
        ProductCategoryResource, CreateProductCategoryResource, CreateProductCategoryResource> {


    public ProductCategoriesController(IProductCategoryService categoryService, ProductCategoryMapper mapper) {
        super(categoryService, mapper);
    }


    @GetMapping
    ResponseEntity<List<ProductCategoryResource>> findAllCategories() {
        return getAll();
    }

    @GetMapping(value = "{id}")
    ResponseEntity<ProductCategoryResource> findCategoryById(@PathVariable Long id) {
        return getById(id);
    }

    @PostMapping
    ResponseEntity<ProductCategoryResource> createCategory(@Valid @RequestBody CreateProductCategoryResource resource, BindingResult result) {
        if (result.hasErrors())
            throw new InvalidCreateResourceException(getErrorsFromResult(result));

        return insert(resource);
    }

    @DeleteMapping(value = "{id}")
    ResponseEntity<ProductCategoryResource> deleteCategory(@PathVariable Long id) {
        return delete(id);
    }

    @Override
    protected boolean isValidCreateResource(CreateProductCategoryResource createProductCategoryResource) {
        return true;
    }

    @Override
    protected boolean isValidUpdateResource(CreateProductCategoryResource createProductCategoryResource) {
        return true;
    }
}
