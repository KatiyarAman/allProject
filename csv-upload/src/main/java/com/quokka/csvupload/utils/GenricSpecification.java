package com.quokka.csvupload.utils;

import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class GenricSpecification {
    public static <T> Specification<T> getSpecs(Map<String, Object> paramMap, String cases) {
        Specification<T> tempSpec = null;
        Specification<T> spec = null;
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {

            switch (cases) {
                case "LIKE":
                    tempSpec = findByColumnLike(entry.getKey(), entry.getValue());
                    spec = spec != null ? Specification.where(spec).or(tempSpec) : tempSpec;
                    break;
                case "AND":
                    tempSpec = findByColumnAnd(entry.getKey(), entry.getValue());
                    spec = spec != null ? Specification.where(spec).and(tempSpec) : tempSpec;
                    break;
                default:
                    tempSpec = findByColumn(entry.getKey(), entry.getValue());
                    spec = spec != null ? Specification.where(spec) : tempSpec;

            }
        }
        return spec;
    }

    private static <T> Specification<T> findByColumn(String key, Object value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(key), value);
    }

    private static <T> Specification<T> findByColumnAnd(String key, Object value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(key), value);
    }

    private static <T> Specification<T> findByColumnLike(String key, Object value) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(key), "%" + value + "%"));
    }
}
