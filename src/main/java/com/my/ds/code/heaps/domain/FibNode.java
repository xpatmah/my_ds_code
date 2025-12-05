package com.my.ds.code.heaps.domain;

public class FibNode<T extends Comparable<T>> {
    private T key;
    private FibNode<T> parent;
    private FibNode<T> child;
    private FibNode<T> left;
    private FibNode<T> right;
    private int degree;
    private boolean mark;

    public FibNode(T key) {
        this.key = key;
        this.parent = null;
        this.child = null;
        this.left = this;
        this.right = this;
        this.degree = 0;
        this.mark = false;
    }

    // getters and setters
    public T getKey() { return key; }
    public void setKey(T key) { this.key = key; }

    public FibNode<T> getParent() { return parent; }
    public void setParent(FibNode<T> parent) { this.parent = parent; }

    public FibNode<T> getChild() { return child; }
    public void setChild(FibNode<T> child) { this.child = child; }

    public FibNode<T> getLeft() { return left; }
    public void setLeft(FibNode<T> left) { this.left = left; }

    public FibNode<T> getRight() { return right; }
    public void setRight(FibNode<T> right) { this.right = right; }

    public int getDegree() { return degree; }
    public void setDegree(int degree) { this.degree = degree; }
    public void incDegree() { this.degree++; }
    public void decDegree() { this.degree--; }

    public boolean isMark() { return mark; }
    public void setMark(boolean mark) { this.mark = mark; }

    @Override
    public String toString() {
        return String.valueOf(key);
    }
}