package com.java.commons.util;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyTree<T> implements Serializable {
    private MyTree<T> parent;
    private T node;
    private List<MyTree<T>> child=new ArrayList<>();

    public MyTree<T> getParent() {
        return parent;
    }
    @JsonBackReference
    public void setParent(MyTree<T> parent) {
        this.parent = parent;
    }

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }

    public List<MyTree<T>> getChild() {
        return child;
    }

    public void setChild(List<MyTree<T>> child) {
        this.child = child;
    }


}
