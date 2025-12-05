package com.my.ds.code.trees.contract;

import com.my.ds.code.trees.domain.TreeNode;

import java.util.List;

/**
 * BinaryTreeInterface - operations expected from a general binary tree
 */
public interface BinaryTreeInterface {
    // traversal methods (recursive)
    void inorderRecursive(TreeNode node);
    void preorderRecursive(TreeNode node);
    void postorderRecursive(TreeNode node);

    // traversal methods (iterative)
    List<Integer> inorderIterative();
    List<Integer> preorderIterative();
    List<Integer> postorderIterative();

    // level-order
    List<Integer> levelOrder();

    // utilities
    int size();
    int height();
    String serialize();                 // serialize to string (preorder with null markers)
    void deserialize(String data);      // rebuild tree from serialized string
}
