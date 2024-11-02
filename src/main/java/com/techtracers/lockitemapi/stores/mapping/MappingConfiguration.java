package com.techtracers.lockitemapi.stores.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("StoreMappingConfiguration")
public class MappingConfiguration {
    @Bean
    StoreMapper storeMapper() {
        return new StoreMapper();
    }

    @Bean
    StoreProductMapper storeProductMapper() {
        return new StoreProductMapper();
    }
}
