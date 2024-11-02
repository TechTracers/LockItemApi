package com.techtracers.lockitemapi.stores.resources;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStoreResource {
    @NotBlank
    @Length(max = 100)
    private String name;

    @NotBlank
    @Length(max = 250)
    @URL
    private String imageUrl;
}
