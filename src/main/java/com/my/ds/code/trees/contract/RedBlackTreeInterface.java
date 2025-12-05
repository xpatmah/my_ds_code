package com.my.ds.code.trees.contract;

import java.util.List;

/**
 * RedBlackTree interface
 */
public interface RedBlackTreeInterface<T extends Comparable<T>> {
    void insert(T key);
    void delete(T key);
    boolean search(T key);

    // Traversals
    List<T> inorder();
    List<T> preorder();
    List<T> postorder();
    List<T> levelOrder();

    // Utilities
    int size();
    int height(); // number of levels (0 for empty)
    T findMin();
    T findMax();
    void clear();
}