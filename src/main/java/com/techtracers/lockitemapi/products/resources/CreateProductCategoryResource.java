package com.techtracers.lockitemapi.products.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCategoryResource {

    @NotBlank
    @Size(max = 50)
    private String name;
}
