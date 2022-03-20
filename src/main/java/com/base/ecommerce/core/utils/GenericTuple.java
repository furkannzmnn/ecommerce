package com.base.ecommerce.core.utils;

import java.util.List;

public class GenericTuple< K, V > {

    private final List<K> left;
    private final List<V> right;


    public GenericTuple(List<K> left, List<V> right) {
        this.left = left;
        this.right = right;
    }

    public GenericTuple() {
        this.left = null;
        this.right = null;
    }

    public List<K> getLeft() {
        return left;
    }

    public List<V> getRight() {
        return right;
    }

}