package com.techtracers.lockitemapi.stores.resources;

import com.techtracers.lockitemapi.products.resources.ProductResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreProductResource {
    private Long id;
    private ProductResource product;
    private Double price;
    private String iotUID;
}
