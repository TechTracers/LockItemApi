package com.techtracers.lockitemapi.products.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("ProductMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ProductMapper productMapper() {
        return new ProductMapper();
    }

    @Bean
    public ProductCategoryMapper productCategoryMapper() {
        return new ProductCategoryMapper();
    }
}
