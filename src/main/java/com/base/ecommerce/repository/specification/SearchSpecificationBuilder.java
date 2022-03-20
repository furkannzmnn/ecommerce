package com.base.ecommerce.repository.specification;

import org.apache.commons.lang3.ArrayUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.Locale;

public class SearchSpecificationBuilder {

    private SearchSpecificationBuilder() {
        throw new IllegalStateException("Utility class");
    }

    public static Predicate or (CriteriaBuilder criteriaBuilder , Predicate predicate1 , Predicate predicate2) {
        if (predicate2 == null && predicate1 == null) {
            return null;
        }
        if (predicate2 != null && predicate1 == null) {
            return predicate2;
        }
        if (predicate2 == null && predicate1 != null) {
            return predicate1;
        }

        return criteriaBuilder.or(predicate1, predicate2);
    }

    public static Predicate and (CriteriaBuilder criteriaBuilder , Predicate predicate1 , Predicate predicate2) {
        if (predicate2 == null && predicate1 == null) {
            return null;
        }
        if (predicate2 != null && predicate1 == null) {
            return predicate2;
        }
        if (predicate2 == null && predicate1 != null) {
            return predicate1;
        }

        return criteriaBuilder.and(predicate1, predicate2);
    }

    public static Predicate iLikeWithOr(CriteriaBuilder builder, Path<String> path, String[] values){
        if (ArrayUtils.isEmpty(values)){
            return null;
        }

        return builder.or(Arrays.stream(values).map(x -> like(builder,path,x)).toArray(Predicate[]::new));
    }

    public static Predicate like (CriteriaBuilder builder, Path<String> path, String value) {
        if (value == null) {
            return null;
        }
        value = value.toLowerCase(Locale.forLanguageTag("tr"));
        String[] valueList = value.split(" ");
        StringBuilder stringBuilder = new StringBuilder();

        if (valueList.length > 1) {
            for (String search : valueList) {
                stringBuilder.append("%").append(search).append("%");
            }
            return builder.like(builder.lower(path), String.valueOf(stringBuilder));
        }
        return builder.like(builder.lower(path), "%" + value + "%");
    }


    public static Predicate eq(CriteriaBuilder criteriaBuilder, Path<Object> path, Object value) {
        if (value == null) {
            return null;
        }
      return  criteriaBuilder.equal(path,value);
    }

    public static Predicate ne(CriteriaBuilder criteriaBuilder, Path<Object> path, Object value) {
        if (value == null) {
            return null;
        }
        return criteriaBuilder.notEqual(path,value);
    }


    public static Predicate isNull(CriteriaBuilder criteriaBuilder, Path<Object> path) {
        return criteriaBuilder.isNull(path);
    }

    public static Predicate isNotNull(CriteriaBuilder criteriaBuilder, Path<Object> path) {
        return criteriaBuilder.isNotNull(path);
    }

    public static Predicate in(CriteriaBuilder criteriaBuilder, Path<Object> path, Object[] value) {
        if (value == null) {
            return null;
        }
        return criteriaBuilder.in(path).value(value);
    }

    public static Predicate notIn(CriteriaBuilder criteriaBuilder, Path<Object> path, Object[] value) {
        if (value == null) {
            return null;
        }
        return criteriaBuilder.not(criteriaBuilder.in(path).value(value));
    }




}
