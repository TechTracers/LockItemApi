package com.techtracers.lockitemapi.stores.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techtracers.lockitemapi.products.domain.model.Product;
import com.techtracers.lockitemapi.stores.domain.model.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStoreProductResource {
    private Long productId;

    @JsonIgnore
    private Product product;

    @JsonIgnore
    private Store store;

    private String iotUID;

    private Double price;
}
