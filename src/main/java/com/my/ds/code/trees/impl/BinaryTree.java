package com.my.ds.code.trees.impl;

import com.my.ds.code.trees.contract.BinaryTreeInterface;
import com.my.ds.code.trees.domain.TreeNode;

import java.util.*;

/* ---------------------------
   BinaryTree: general binary tree
   --------------------------- */
public class BinaryTree implements BinaryTreeInterface {
    protected TreeNode root;

    public BinaryTree() { root = null; }

    public TreeNode getRoot() { return root; }
    public void setRoot(TreeNode node) { this.root = node; }

    /**
     * Insert in level-order so tree is as complete as possible.
     * (Useful for demos / building a simple binary tree.)
     */
    public void insertLevelOrder(int value) {
        TreeNode newNode = new TreeNode(value);
        if (root == null) {
            root = newNode;
            return;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.getLeft() == null) {
                cur.setLeft(newNode);
                return;
            } else q.add(cur.getLeft());

            if (cur.getRight() == null) {
                cur.setRight(newNode);
                return;
            } else q.add(cur.getRight());
        }
    }

    // ---------------------
    // Recursive traversals
    // ---------------------
    @Override
    public void inorderRecursive(TreeNode node) {
        if (node == null) return;
        inorderRecursive(node.getLeft());
        System.out.print(node.getData() + " ");
        inorderRecursive(node.getRight());
    }

    @Override
    public void preorderRecursive(TreeNode node) {
        if (node == null) return;
        System.out.print(node.getData() + " ");
        preorderRecursive(node.getLeft());
        preorderRecursive(node.getRight());
    }

    @Override
    public void postorderRecursive(TreeNode node) {
        if (node == null) return;
        postorderRecursive(node.getLeft());
        postorderRecursive(node.getRight());
        System.out.print(node.getData() + " ");
    }

    // ---------------------
    // Iterative traversals (stack-based)
    // ---------------------

    @Override
    public List<Integer> inorderIterative() {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) { stack.push(curr); curr = curr.getLeft(); }
            curr = stack.pop();
            res.add(curr.getData());
            curr = curr.getRight();
        }
        return res;
    }

    @Override
    public List<Integer> preorderIterative() {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.getData());
            // push right first so left is processed first
            if (node.getRight() != null) stack.push(node.getRight());
            if (node.getLeft() != null) stack.push(node.getLeft());
        }
        return res;
    }

    @Override
    public List<Integer> postorderIterative() {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        // Approach: use two stacks or one stack with previous pointer
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode prev = null;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.peek();
            // going down the tree
            if (prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
                if (curr.getLeft() != null) stack.push(curr.getLeft());
                else if (curr.getRight() != null) stack.push(curr.getRight());
                else {
                    stack.pop();
                    res.add(curr.getData());
                }
            }
            // coming up from left
            else if (curr.getLeft() == prev) {
                if (curr.getRight() != null) stack.push(curr.getRight());
                else {
                    stack.pop();
                    res.add(curr.getData());
                }
            }
            // coming up from right
            else if (curr.getRight() == prev) {
                stack.pop();
                res.add(curr.getData());
            }
            prev = curr;
        }
        return res;
    }

    // ---------------------
    // Level-order traversal (BFS)
    // ---------------------
    @Override
    public List<Integer> levelOrder() {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            res.add(node.getData());
            if (node.getLeft() != null) q.add(node.getLeft());
            if (node.getRight() != null) q.add(node.getRight());
        }
        return res;
    }

    // ---------------------
    // Utilities
    // ---------------------
    @Override
    public int size() {
        return sizeRec(root);
    }

    private int sizeRec(TreeNode node) {
        if (node == null) return 0;
        return 1 + sizeRec(node.getLeft()) + sizeRec(node.getRight());
    }

    @Override
    public int height() {
        return heightRec(root);
    }

    private int heightRec(TreeNode node) {
        if (node == null) return -1; // define empty tree height = -1, single node = 0
        return 1 + Math.max(heightRec(node.getLeft()), heightRec(node.getRight()));
    }

    // Serialize (preorder with '#' markers for nulls)
    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        serializeRec(root, sb);
        return sb.toString();
    }

    private void serializeRec(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#,");
            return;
        }
        sb.append(node.getData()).append(",");
        serializeRec(node.getLeft(), sb);
        serializeRec(node.getRight(), sb);
    }

    // Deserialize
    @Override
    public void deserialize(String data) {
        if (data == null || data.isEmpty()) { root = null; return; }
        String[] tokens = data.split(",");
        Index idx = new Index();
        root = deserializeRec(tokens, idx);
    }

    private static class Index { int pos = 0; }

    private TreeNode deserializeRec(String[] tokens, Index idx) {
        if (idx.pos >= tokens.length) return null;
        String token = tokens[idx.pos++];
        if (token.equals("#")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(token));
        node.setLeft(deserializeRec(tokens, idx));
        node.setRight(deserializeRec(tokens, idx));
        return node;
    }
}
