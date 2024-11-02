package com.techtracers.lockitemapi.shared.mapping;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class EnhancedModelMapper extends ModelMapper {
    public EnhancedModelMapper() {
        super();
    }

    public <T, R> List<R> mapList(List<T> source, Class<R> destination) {
        return source.stream()
                .map(item -> this.map(item, destination))
                .collect(Collectors.toList());
    }
}
