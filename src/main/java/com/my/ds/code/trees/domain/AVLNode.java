package com.my.ds.code.trees.domain;

import java.util.*;

/**
 * AVL Node with getters and setters.
 */
public class AVLNode<T> {
    private T key;
    private AVLNode<T> left;
    private AVLNode<T> right;
    private int height;

    public AVLNode(T key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.height = 1; // leaf node has height 1
    }

    // getters
    public T getKey() { return key; }
    public AVLNode<T> getLeft() { return left; }
    public AVLNode<T> getRight() { return right; }
    public int getHeight() { return height; }

    // setters
    public void setKey(T key) { this.key = key; }
    public void setLeft(AVLNode<T> left) { this.left = left; }
    public void setRight(AVLNode<T> right) { this.right = right; }
    public void setHeight(int height) { this.height = height; }
}