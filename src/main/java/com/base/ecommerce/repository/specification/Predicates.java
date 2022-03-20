package com.base.ecommerce.repository.specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Predicates {

    List<Predicate> predicates = new ArrayList<>();

    public Predicate[] get() {
        return  predicates
                .stream()
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new);
    }

    public Predicates add(Predicate predicate) {
        if (predicate != null) {
            predicates.add(predicate);
        }
        return this;
    }
}
