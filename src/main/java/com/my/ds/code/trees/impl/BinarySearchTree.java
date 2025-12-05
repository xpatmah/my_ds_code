package com.my.ds.code.trees.impl;

import com.my.ds.code.trees.contract.BinarySearchTreeInterface;
import com.my.ds.code.trees.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.*;

/* ---------------------------
   BinarySearchTree: BST implementation
   --------------------------- */
public class BinarySearchTree extends BinaryTree implements BinarySearchTreeInterface {

    public BinarySearchTree() { super(); }

    @Override
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.getData()) node.setLeft(insertRec(node.getLeft(), val));
        else if (val > node.getData()) node.setRight(insertRec(node.getRight(), val));
        // duplicates ignored
        return node;
    }

    @Override
    public boolean search(int value) {
        TreeNode cur = root;
        while (cur != null) {
            if (value == cur.getData()) return true;
            cur = (value < cur.getData()) ? cur.getLeft() : cur.getRight();
        }
        return false;
    }

    @Override
    public void delete(int value) {
        root = deleteRec(root, value);
    }

    private TreeNode deleteRec(TreeNode node, int val) {
        if (node == null) return null;
        if (val < node.getData()) node.setLeft(deleteRec(node.getLeft(), val));
        else if (val > node.getData()) node.setRight(deleteRec(node.getRight(), val));
        else {
            // node to delete
            if (node.getLeft() == null && node.getRight() == null) return null;
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();
            // both children: replace with inorder successor (min in right)
            TreeNode succ = findMinNode(node.getRight());
            node.setData(succ.getData());
            node.setRight(deleteRec(node.getRight(), succ.getData()));
        }
        return node;
    }

    private TreeNode findMinNode(TreeNode node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node;
    }

    @Override
    public Integer findMin() {
        if (root == null) return null;
        TreeNode cur = root;
        while (cur.getLeft() != null) cur = cur.getLeft();
        return cur.getData();
    }

    @Override
    public Integer findMax() {
        if (root == null) return null;
        TreeNode cur = root;
        while (cur.getRight() != null) cur = cur.getRight();
        return cur.getData();
    }

    // Iterative inorder/preorder/postorder reuse BinaryTree methods, but they operate on root.
    @Override
    public List<Integer> inorderIterative() { return super.inorderIterative(); }

    @Override
    public List<Integer> preorderIterative() { return super.preorderIterative(); }

    @Override
    public List<Integer> postorderIterative() { return super.postorderIterative(); }

    @Override
    public List<Integer> levelOrder() { return super.levelOrder(); }

    @Override
    public int size() { return super.size(); }

    @Override
    public int height() { return super.height(); }

    @Override
    public boolean isValidBST() {
        return isValidBSTRec(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTRec(TreeNode node, long min, long max) {
        if (node == null) return true;
        long v = node.getData();
        if (v <= min || v >= max) return false;
        return isValidBSTRec(node.getLeft(), min, v) && isValidBSTRec(node.getRight(), v, max);
    }

    @Override
    public Integer kthSmallest(int k) {
        if (k <= 0) return null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        int count = 0;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) { stack.push(curr); curr = curr.getLeft(); }
            curr = stack.pop();
            count++;
            if (count == k) return curr.getData();
            curr = curr.getRight();
        }
        return null;
    }

    // Serialize/deserialize use BinaryTree implementation (preorder + null markers)
    @Override
    public String serialize() { return super.serialize(); }

    @Override
    public void deserialize(String data) { super.deserialize(data); }
}
