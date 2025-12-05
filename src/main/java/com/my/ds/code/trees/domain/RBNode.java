package com.my.ds.code.trees.domain;

import java.util.*;

/**
 * RBNode: node class for Red-Black Tree with getters and setters.
 */
public class RBNode<T> {
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private T key;
    private RBNode<T> left;
    private RBNode<T> right;
    private RBNode<T> parent;
    private boolean color; // true = RED, false = BLACK

    public RBNode(T key, boolean color, RBNode<T> nil) {
        this.key = key;
        this.color = color;
        // children/parent initially point to sentinel nil (set by tree)
        this.left = nil;
        this.right = nil;
        this.parent = nil;
    }

    // Getters
    public T getKey() { return key; }
    public RBNode<T> getLeft() { return left; }
    public RBNode<T> getRight() { return right; }
    public RBNode<T> getParent() { return parent; }
    public boolean getColor() { return color; }

    // Setters
    public void setKey(T key) { this.key = key; }
    public void setLeft(RBNode<T> left) { this.left = left; }
    public void setRight(RBNode<T> right) { this.right = right; }
    public void setParent(RBNode<T> parent) { this.parent = parent; }
    public void setColor(boolean color) { this.color = color; }

    @Override
    public String toString() {
        return String.valueOf(key) + (color == RED ? "(R)" : "(B)");
    }
}