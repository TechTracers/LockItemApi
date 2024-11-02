package com.techtracers.lockitemapi.products.resources;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductResource {
    @NotBlank
    @Length(max = 50)
    private String name;

    @NotBlank
    @Length(max = 500)
    private String description;

    @NotBlank
    @Length(max = 250)
    @URL
    private String imageUrl;

}
