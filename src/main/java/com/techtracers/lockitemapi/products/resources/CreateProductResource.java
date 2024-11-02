package com.techtracers.lockitemapi.products.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techtracers.lockitemapi.products.domain.model.ProductCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductResource extends UpdateProductResource {

    @NotNull
    @Positive
    private Long categoryId;

    @JsonIgnore
    private ProductCategory category;
}
