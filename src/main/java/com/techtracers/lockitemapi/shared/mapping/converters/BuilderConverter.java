package com.techtracers.lockitemapi.shared.mapping.converters;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.lang.reflect.Method;
import java.util.Arrays;

public class BuilderConverter<S, D> implements Converter<S, D> {
    private final String prefix;

    public BuilderConverter() {
        this("");
    }



    public BuilderConverter(String prefix) {
        this.prefix = prefix;
    }

    public boolean hasPrefix() {
        return prefix != null && !prefix.isEmpty();
    }

    @Override
    public D convert(MappingContext<S, D> context) {
        if (context.getDestination() != null)
            throw new IllegalArgumentException("This converter cannot map in-place properties.");

        S source = context.getSource();
        Class<D> type = context.getDestinationType();

        Class<?> builderClass = Arrays.stream(type.getDeclaredClasses())
                .filter(value -> value.getSimpleName().endsWith("Builder"))
                .findFirst()
                .orElseThrow();

        Object builder;

        try {
            try {
                builder = builderClass.getDeclaredConstructor()
                        .newInstance();
            } catch (IllegalAccessException e) {
                builder = type.getDeclaredMethod("builder")
                        .invoke(null);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not instantiate builder class " + builderClass, e);
        }

        try {
            Method[] methods = type.getDeclaredMethods();

            for (Method method : methods) {
                String propertyName = method.getName();
                if (propertyName.startsWith("get")) {
                    propertyName = propertyName.substring(3);
                    try {
                        Object value = source.getClass()
                                .getMethod("get" + propertyName)
                                .invoke(source);
                        if (value == null)
                            continue;

                        if (!this.hasPrefix())
                            propertyName = Character.toLowerCase(propertyName.charAt(0)) + propertyName.substring(1);

                        builderClass.getDeclaredMethod(this.prefix + propertyName, value.getClass())
                                .invoke(builder, value);
                    } catch (NoSuchMethodException | NullPointerException ignored) {
                    }
                }
            }

            return (D) builderClass.getDeclaredMethod("build")
                    .invoke(builder);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not map the classes " + builderClass, e);
        }
    }
}
