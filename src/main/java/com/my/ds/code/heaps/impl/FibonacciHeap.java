package com.my.ds.code.heaps.impl;

import com.my.ds.code.heaps.contract.FibonacciHeapInterface;
import com.my.ds.code.heaps.domain.FibNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FibonacciHeap<T extends Comparable<T>> implements FibonacciHeapInterface<T> {
    private FibNode<T> min;
    private int n; // number of nodes

    public FibonacciHeap() {
        min = null;
        n = 0;
    }

    @Override
    public FibNode<T> insert(T key) {
        FibNode<T> node = new FibNode<>(key);
        // add to root list
        if (min == null) {
            min = node;
        } else {
            // insert node into root list (to the right of min)
            node.setLeft(min);
            node.setRight(min.getRight());
            min.getRight().setLeft(node);
            min.setRight(node);
            if (key.compareTo(min.getKey()) < 0) min = node;
        }
        n++;
        return node;
    }

    @Override
    public T findMin() {
        return min == null ? null : min.getKey();
    }

    @Override
    public T extractMin() {
        FibNode<T> z = min;
        if (z != null) {
            // for each child of z, add to root list
            List<FibNode<T>> children = new ArrayList<>();
            FibNode<T> c = z.getChild();
            if (c != null) {
                FibNode<T> start = c;
                do {
                    children.add(c);
                    c = c.getRight();
                } while (c != start);
            }
            for (FibNode<T> child : children) {
                // remove parent link
                child.setParent(null);
                // add child to root list (insert right of min)
                child.getLeft().setRight(child.getRight());
                child.getRight().setLeft(child.getLeft());
                child.setLeft(min);
                child.setRight(min.getRight());
                min.getRight().setLeft(child);
                min.setRight(child);
            }
            // remove z from root list
            z.getLeft().setRight(z.getRight());
            z.getRight().setLeft(z.getLeft());
            if (z == z.getRight()) {
                min = null;
            } else {
                min = z.getRight();
                consolidate();
            }
            n--;
            return z.getKey();
        }
        return null;
    }

    private void consolidate() {
        int arraySize = Math.max(1, (int) Math.floor(Math.log(n) / Math.log(2)) + 2);
        List<FibNode<T>> A = new ArrayList<>(Collections.nCopies(arraySize, null));

        // iterate over root list and link trees of same degree
        List<FibNode<T>> roots = new ArrayList<>();
        FibNode<T> x = min;
        if (x != null) {
            FibNode<T> start = x;
            do {
                roots.add(x);
                x = x.getRight();
            } while (x != start);
        }

        for (FibNode<T> w : roots) {
            x = w;
            int d = x.getDegree();
            while (d >= A.size()) {
                A.add(null);
            }
            while (A.get(d) != null) {
                FibNode<T> y = A.get(d);
                if (x.getKey().compareTo(y.getKey()) > 0) {
                    // swap x and y
                    FibNode<T> tmp = x;
                    x = y;
                    y = tmp;
                }
                link(y, x);
                A.set(d, null);
                d++;
                while (d >= A.size()) A.add(null);
            }
            A.set(d, x);
        }

        min = null;
        for (FibNode<T> node : A) {
            if (node != null) {
                // isolate node
                node.setLeft(node);
                node.setRight(node);
                if (min == null) min = node;
                else {
                    // insert into root list
                    node.setLeft(min);
                    node.setRight(min.getRight());
                    min.getRight().setLeft(node);
                    min.setRight(node);
                    if (node.getKey().compareTo(min.getKey()) < 0) min = node;
                }
            }
        }
    }

    private void link(FibNode<T> y, FibNode<T> x) {
        // remove y from root list
        y.getLeft().setRight(y.getRight());
        y.getRight().setLeft(y.getLeft());
        // make y a child of x
        y.setParent(x);
        if (x.getChild() == null) {
            x.setChild(y);
            y.setLeft(y);
            y.setRight(y);
        } else {
            FibNode<T> c = x.getChild();
            y.setLeft(c);
            y.setRight(c.getRight());
            c.getRight().setLeft(y);
            c.setRight(y);
        }
        x.incDegree();
        y.setMark(false);
    }

    @Override
    public void decreaseKey(FibNode<T> x, T k) {
        if (x == null) throw new IllegalArgumentException("Node is null");
        if (k.compareTo(x.getKey()) > 0) throw new IllegalArgumentException("New key is greater than current key");
        x.setKey(k);
        FibNode<T> y = x.getParent();
        if (y != null && x.getKey().compareTo(y.getKey()) < 0) {
            cut(x, y);
            cascadingCut(y);
        }
        if (x.getKey().compareTo(min.getKey()) < 0) min = x;
    }

    private void cut(FibNode<T> x, FibNode<T> y) {
        // remove x from child list of y
        if (y.getChild() == x) {
            if (x.getRight() != x) y.setChild(x.getRight());
            else y.setChild(null);
        }
        x.getLeft().setRight(x.getRight());
        x.getRight().setLeft(x.getLeft());
        y.decDegree();

        // add x to root list
        x.setLeft(min);
        x.setRight(min.getRight());
        min.getRight().setLeft(x);
        min.setRight(x);
        x.setParent(null);
        x.setMark(false);
    }

    private void cascadingCut(FibNode<T> y) {
        FibNode<T> z = y.getParent();
        if (z != null) {
            if (!y.isMark()) y.setMark(true);
            else {
                cut(y, z);
                cascadingCut(z);
            }
        }
    }

    @Override
    public void delete(FibNode<T> x) {
        // standard delete: decrease key to -inf and extractMin
        // We cannot create -inf for generic T, so we simulate by swapping x.key with current min minus special handling:
        // We'll repeatedly decrease x to be smaller than current min by performing comparisons with min and using a wrapper.
        // Simpler approach: decrease key to the current minimum (if x is not min) then extractMin repeatedly until x removed.
        // But that risks complexity. Instead require user to call decreaseKey to a value smaller than current min before delete.
        // To still offer delete, we'll implement: decreaseKey(x, verySmall) if T is Comparable and min is non-null:
        if (x == null) return;
        // If x is already min, just extractMin
        if (x == min) {
            extractMin();
            return;
        }
        // As we can't fabricate a guaranteed smaller key generically, we'll try to set key to min.key and then perform cuts so x becomes root and then remove it:
        // This is not a general numeric -inf deletion but works if keys can be equal. We still perform cutting and removal from root list.
        decreaseKeyToMinPosition(x);
        extractMin();
    }

    // helper used in delete when newKey not provided: force x up by setting key equal to min and cutting
    private void decreaseKeyToMinPosition(FibNode<T> x) {
        if (min == null) return;
        // set x.key to something <= min.key by setting equal (tie-break will still move x up via cuts if needed)
        x.setKey(min.getKey());
        FibNode<T> y = x.getParent();
        if (y != null && x.getKey().compareTo(y.getKey()) < 0) {
            cut(x, y);
            cascadingCut(y);
        }
        if (x.getKey().compareTo(min.getKey()) <= 0) min = x;
    }

    @Override
    public void merge(FibonacciHeap<T> other) {
        if (other == null || other.min == null) return;
        if (this.min == null) {
            this.min = other.min;
            this.n = other.n;
            return;
        }
        // concatenate root lists: min.right <-> other.min.left
        FibNode<T> thisRight = this.min.getRight();
        FibNode<T> otherLeft = other.min.getLeft();

        this.min.setRight(other.min.getRight());
        other.min.getRight().setLeft(this.min);

        other.min.setRight(thisRight);
        thisRight.setLeft(other.min);

        if (other.min.getKey().compareTo(this.min.getKey()) < 0) this.min = other.min;
        this.n += other.n;
        // empty other
        other.min = null;
        other.n = 0;
    }

    @Override
    public boolean isEmpty() {
        return min == null;
    }

    @Override
    public int size() {
        return n;
    }
}