package com.techtracers.lockitemapi.stores.resources;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreProductFilters {
    private String name;
    private Long id;
}
