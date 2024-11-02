package com.techtracers.lockitemapi.stores.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techtracers.lockitemapi.products.domain.model.Product;
import com.techtracers.lockitemapi.stores.domain.model.Store;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStoreProductResource {
    @NotNull
    private Long productId;

    @JsonIgnore
    private Product product;

    @JsonIgnore
    private Store store;

    private String iotUID;

    public String getIotUID() {
        if(iotUID == null)
            return "NO_DEFINED";
        return iotUID;
    }

    @Positive
    private Double price;
}
