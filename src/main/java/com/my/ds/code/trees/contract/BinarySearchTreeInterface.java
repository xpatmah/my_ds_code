package com.my.ds.code.trees.contract;

/**
 * BinarySearchTreeInterface - BST-specific operations
 */
public interface BinarySearchTreeInterface extends BinaryTreeInterface {
    void insert(int value);
    boolean search(int value);
    void delete(int value);
    Integer findMin();
    Integer findMax();
    Integer kthSmallest(int k); // 1-based k
    boolean isValidBST();
}