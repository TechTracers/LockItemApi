package com.techtracers.lockitemapi.products.resources;

import com.techtracers.lockitemapi.products.domain.model.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResource {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private ProductCategory category;
}
