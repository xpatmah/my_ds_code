package com.my.ds.code.trees.impl;

import com.my.ds.code.trees.contract.AVLTreeInterface;
import com.my.ds.code.trees.domain.AVLNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * AVL Tree implementation
 */
public class AVLTree<T extends Comparable<T>> implements AVLTreeInterface<T> {
    private AVLNode<T> root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    // ---------- Public API ----------

    @Override
    public void insert(T key) {
        if (key == null) throw new IllegalArgumentException("Key must not be null");
        root = insertRec(root, key);
    }

    @Override
    public void delete(T key) {
        if (key == null) return;
        root = deleteRec(root, key);
    }

    @Override
    public boolean search(T key) {
        return searchRec(root, key);
    }

    @Override
    public List<T> inorder() {
        List<T> out = new ArrayList<>();
        inorderRec(root, out);
        return out;
    }

    @Override
    public List<T> preorder() {
        List<T> out = new ArrayList<>();
        preorderRec(root, out);
        return out;
    }

    @Override
    public List<T> postorder() {
        List<T> out = new ArrayList<>();
        postorderRec(root, out);
        return out;
    }

    @Override
    public List<T> levelOrder() {
        List<T> out = new ArrayList<>();
        if (root == null) return out;
        Queue<AVLNode<T>> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            AVLNode<T> n = q.poll();
            out.add(n.getKey());
            if (n.getLeft() != null) q.add(n.getLeft());
            if (n.getRight() != null) q.add(n.getRight());
        }
        return out;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return root == null ? 0 : root.getHeight();
    }

    @Override
    public T findMin() {
        if (root == null) return null;
        AVLNode<T> cur = root;
        while (cur.getLeft() != null) cur = cur.getLeft();
        return cur.getKey();
    }

    @Override
    public T findMax() {
        if (root == null) return null;
        AVLNode<T> cur = root;
        while (cur.getRight() != null) cur = cur.getRight();
        return cur.getKey();
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    // ---------- Internal helpers ----------

    private AVLNode<T> insertRec(AVLNode<T> node, T key) {
        if (node == null) {
            size++;
            return new AVLNode<>(key);
        }

        int cmp = key.compareTo(node.getKey());
        if (cmp < 0) {
            node.setLeft(insertRec(node.getLeft(), key));
        } else if (cmp > 0) {
            node.setRight(insertRec(node.getRight(), key));
        } else {
            // duplicate: update key or ignore. We'll ignore duplicates.
            return node;
        }

        // update height and rebalance
        updateHeight(node);
        return rebalance(node);
    }

    private AVLNode<T> deleteRec(AVLNode<T> node, T key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.getKey());
        if (cmp < 0) {
            node.setLeft(deleteRec(node.getLeft(), key));
        } else if (cmp > 0) {
            node.setRight(deleteRec(node.getRight(), key));
        } else {
            // node found
            size--;
            // node with one child or no child
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                // node with two children: get inorder successor (min in right)
                AVLNode<T> succ = node.getRight();
                while (succ.getLeft() != null) succ = succ.getLeft();
                node.setKey(succ.getKey());
                node.setRight(deleteRec(node.getRight(), succ.getKey()));
            }
        }

        // update height and rebalance
        updateHeight(node);
        return rebalance(node);
    }

    private boolean searchRec(AVLNode<T> node, T key) {
        if (node == null) return false;
        int cmp = key.compareTo(node.getKey());
        if (cmp == 0) return true;
        if (cmp < 0) return searchRec(node.getLeft(), key);
        else return searchRec(node.getRight(), key);
    }

    // Update node height from children
    private void updateHeight(AVLNode<T> node) {
        int lh = node.getLeft() == null ? 0 : node.getLeft().getHeight();
        int rh = node.getRight() == null ? 0 : node.getRight().getHeight();
        node.setHeight(Math.max(lh, rh) + 1);
    }

    private int getBalance(AVLNode<T> node) {
        if (node == null) return 0;
        int lh = node.getLeft() == null ? 0 : node.getLeft().getHeight();
        int rh = node.getRight() == null ? 0 : node.getRight().getHeight();
        return lh - rh;
    }

    // Rebalance node if needed and return the (possibly new) root of this subtree
    private AVLNode<T> rebalance(AVLNode<T> node) {
        int balance = getBalance(node);

        // Left heavy
        if (balance > 1) {
            if (getBalance(node.getLeft()) < 0) {
                // Left-Right case
                node.setLeft(leftRotate(node.getLeft()));
            }
            // Left-Left
            return rightRotate(node);
        }

        // Right heavy
        if (balance < -1) {
            if (getBalance(node.getRight()) > 0) {
                // Right-Left case
                node.setRight(rightRotate(node.getRight()));
            }
            // Right-Right
            return leftRotate(node);
        }

        // already balanced
        return node;
    }

    // Right rotation
    private AVLNode<T> rightRotate(AVLNode<T> y) {
        AVLNode<T> x = y.getLeft();
        AVLNode<T> T2 = x.getRight();

        // perform rotation
        x.setRight(y);
        y.setLeft(T2);

        // update heights
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // Left rotation
    private AVLNode<T> leftRotate(AVLNode<T> x) {
        AVLNode<T> y = x.getRight();
        AVLNode<T> T2 = y.getLeft();

        // perform rotation
        y.setLeft(x);
        x.setRight(T2);

        // update heights
        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // ---------- Traversal helpers ----------

    private void inorderRec(AVLNode<T> node, List<T> out) {
        if (node == null) return;
        inorderRec(node.getLeft(), out);
        out.add(node.getKey());
        inorderRec(node.getRight(), out);
    }

    private void preorderRec(AVLNode<T> node, List<T> out) {
        if (node == null) return;
        out.add(node.getKey());
        preorderRec(node.getLeft(), out);
        preorderRec(node.getRight(), out);
    }

    private void postorderRec(AVLNode<T> node, List<T> out) {
        if (node == null) return;
        postorderRec(node.getLeft(), out);
        postorderRec(node.getRight(), out);
        out.add(node.getKey());
    }
}
