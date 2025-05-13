package com.example.ticket.management.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BeanMapperUtils {

    public void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private String[] getNullPropertyNames(Object source) {
        final var src = new org.springframework.beans.BeanWrapperImpl(source);
        return java.util.Arrays.stream(src.getPropertyDescriptors())
                .map(pd -> pd.getName())
                .filter(name -> src.getPropertyValue(name) == null)
                .toArray(String[]::new);
    }
}