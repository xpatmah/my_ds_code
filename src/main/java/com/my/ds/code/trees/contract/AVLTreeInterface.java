package com.my.ds.code.trees.contract;

import java.util.List;

/**
 * AVL Tree interface
 */
public interface AVLTreeInterface<T extends Comparable<T>> {
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
    int height(); // height of tree (0 if empty, or number of levels)
    T findMin();
    T findMax();
    void clear();
}