package com.my.ds.code.trees.impl;

import com.my.ds.code.trees.contract.RedBlackTreeInterface;
import com.my.ds.code.trees.domain.RBNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Red-Black Tree implementation (CLRS style) using sentinel nil node.
 */
public class RedBlackTree<T extends Comparable<T>> implements RedBlackTreeInterface<T> {
    private final RBNode<T> nil; // sentinel leaf (shared)
    private RBNode<T> root;
    private int size;

    public RedBlackTree() {
        // Create sentinel nil node. Its children/parent point to itself.
        nil = new RBNode<>(null, RBNode.BLACK, null);
        nil.setLeft(nil);
        nil.setRight(nil);
        nil.setParent(nil);
        root = nil;
        size = 0;
    }

    /* -------------------------
       Public API
       ------------------------- */

    @Override
    public void insert(T key) {
        if (key == null) throw new IllegalArgumentException("Key must not be null");
        RBNode<T> z = new RBNode<>(key, RBNode.RED, nil);
        z.setLeft(nil);
        z.setRight(nil);
        z.setParent(nil);

        RBNode<T> y = nil;
        RBNode<T> x = root;
        while (x != nil) {
            y = x;
            int cmp = key.compareTo(x.getKey());
            if (cmp == 0) return; // ignore duplicates
            else if (cmp < 0) x = x.getLeft();
            else x = x.getRight();
        }
        z.setParent(y);
        if (y == nil) root = z;
        else if (key.compareTo(y.getKey()) < 0) y.setLeft(z);
        else y.setRight(z);

        size++;
        insertFixup(z);
    }

    @Override
    public void delete(T key) {
        RBNode<T> z = searchNode(root, key);
        if (z == nil) return; // not found
        deleteNode(z);
        size--;
    }

    @Override
    public boolean search(T key) {
        return searchNode(root, key) != nil;
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
        if (root == nil) return out;
        Queue<RBNode<T>> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            RBNode<T> n = q.poll();
            out.add(n.getKey());
            if (n.getLeft() != nil) q.add(n.getLeft());
            if (n.getRight() != nil) q.add(n.getRight());
        }
        return out;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        if (root == nil) return 0;
        return heightRec(root);
    }

    @Override
    public T findMin() {
        if (root == nil) return null;
        RBNode<T> cur = minimum(root);
        return cur == nil ? null : cur.getKey();
    }

    @Override
    public T findMax() {
        if (root == nil) return null;
        RBNode<T> cur = maximum(root);
        return cur == nil ? null : cur.getKey();
    }

    @Override
    public void clear() {
        root = nil;
        size = 0;
    }

    /* -------------------------
       Internal helpers: rotations, fixups, search, min/max
       ------------------------- */

    private void leftRotate(RBNode<T> x) {
        RBNode<T> y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != nil) y.getLeft().setParent(x);
        y.setParent(x.getParent());
        if (x.getParent() == nil) root = y;
        else if (x == x.getParent().getLeft()) x.getParent().setLeft(y);
        else x.getParent().setRight(y);
        y.setLeft(x);
        x.setParent(y);
    }

    private void rightRotate(RBNode<T> y) {
        RBNode<T> x = y.getLeft();
        y.setLeft(x.getRight());
        if (x.getRight() != nil) x.getRight().setParent(y);
        x.setParent(y.getParent());
        if (y.getParent() == nil) root = x;
        else if (y == y.getParent().getRight()) y.getParent().setRight(x);
        else y.getParent().setLeft(x);
        x.setRight(y);
        y.setParent(x);
    }

    // Standard RB-Insert-Fixup from CLRS
    private void insertFixup(RBNode<T> z) {
        while (z.getParent().getColor() == RBNode.RED) {
            if (z.getParent() == z.getParent().getParent().getLeft()) {
                RBNode<T> y = z.getParent().getParent().getRight(); // uncle
                if (y.getColor() == RBNode.RED) {
                    // case 1
                    z.getParent().setColor(RBNode.BLACK);
                    y.setColor(RBNode.BLACK);
                    z.getParent().getParent().setColor(RBNode.RED);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getRight()) {
                        // case 2
                        z = z.getParent();
                        leftRotate(z);
                    }
                    // case 3
                    z.getParent().setColor(RBNode.BLACK);
                    z.getParent().getParent().setColor(RBNode.RED);
                    rightRotate(z.getParent().getParent());
                }
            } else {
                // mirror: parent is right child
                RBNode<T> y = z.getParent().getParent().getLeft();
                if (y.getColor() == RBNode.RED) {
                    // case 1
                    z.getParent().setColor(RBNode.BLACK);
                    y.setColor(RBNode.BLACK);
                    z.getParent().getParent().setColor(RBNode.RED);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getLeft()) {
                        // case 2
                        z = z.getParent();
                        rightRotate(z);
                    }
                    // case 3
                    z.getParent().setColor(RBNode.BLACK);
                    z.getParent().getParent().setColor(RBNode.RED);
                    leftRotate(z.getParent().getParent());
                }
            }
            if (z == root) break;
        }
        root.setColor(RBNode.BLACK);
    }

    // Transplant: replace u by v in tree
    private void transplant(RBNode<T> u, RBNode<T> v) {
        if (u.getParent() == nil) root = v;
        else if (u == u.getParent().getLeft()) u.getParent().setLeft(v);
        else u.getParent().setRight(v);
        v.setParent(u.getParent());
    }

    private void deleteNode(RBNode<T> z) {
        RBNode<T> y = z;
        boolean yOriginalColor = y.getColor();
        RBNode<T> x;
        if (z.getLeft() == nil) {
            x = z.getRight();
            transplant(z, z.getRight());
        } else if (z.getRight() == nil) {
            x = z.getLeft();
            transplant(z, z.getLeft());
        } else {
            y = minimum(z.getRight());
            yOriginalColor = y.getColor();
            x = y.getRight();
            if (y.getParent() == z) {
                x.setParent(y);
            } else {
                transplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());
        }
        if (yOriginalColor == RBNode.BLACK) deleteFixup(x);
    }

    // Standard RB-Delete-Fixup from CLRS
    private void deleteFixup(RBNode<T> x) {
        while (x != root && x.getColor() == RBNode.BLACK) {
            if (x == x.getParent().getLeft()) {
                RBNode<T> w = x.getParent().getRight();
                if (w.getColor() == RBNode.RED) {
                    // case 1
                    w.setColor(RBNode.BLACK);
                    x.getParent().setColor(RBNode.RED);
                    leftRotate(x.getParent());
                    w = x.getParent().getRight();
                }
                if (w.getLeft().getColor() == RBNode.BLACK && w.getRight().getColor() == RBNode.BLACK) {
                    // case 2
                    w.setColor(RBNode.RED);
                    x = x.getParent();
                } else {
                    if (w.getRight().getColor() == RBNode.BLACK) {
                        // case 3
                        w.getLeft().setColor(RBNode.BLACK);
                        w.setColor(RBNode.RED);
                        rightRotate(w);
                        w = x.getParent().getRight();
                    }
                    // case 4
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(RBNode.BLACK);
                    w.getRight().setColor(RBNode.BLACK);
                    leftRotate(x.getParent());
                    x = root;
                }
            } else {
                // mirror
                RBNode<T> w = x.getParent().getLeft();
                if (w.getColor() == RBNode.RED) {
                    // case 1
                    w.setColor(RBNode.BLACK);
                    x.getParent().setColor(RBNode.RED);
                    rightRotate(x.getParent());
                    w = x.getParent().getLeft();
                }
                if (w.getRight().getColor() == RBNode.BLACK && w.getLeft().getColor() == RBNode.BLACK) {
                    // case 2
                    w.setColor(RBNode.RED);
                    x = x.getParent();
                } else {
                    if (w.getLeft().getColor() == RBNode.BLACK) {
                        // case 3
                        w.getRight().setColor(RBNode.BLACK);
                        w.setColor(RBNode.RED);
                        leftRotate(w);
                        w = x.getParent().getLeft();
                    }
                    // case 4
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(RBNode.BLACK);
                    w.getLeft().setColor(RBNode.BLACK);
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(RBNode.BLACK);
    }

    private RBNode<T> searchNode(RBNode<T> start, T key) {
        RBNode<T> cur = start;
        while (cur != nil) {
            int cmp = key.compareTo(cur.getKey());
            if (cmp == 0) return cur;
            else if (cmp < 0) cur = cur.getLeft();
            else cur = cur.getRight();
        }
        return nil;
    }

    private RBNode<T> minimum(RBNode<T> node) {
        RBNode<T> cur = node;
        while (cur.getLeft() != nil) cur = cur.getLeft();
        return cur;
    }

    private RBNode<T> maximum(RBNode<T> node) {
        RBNode<T> cur = node;
        while (cur.getRight() != nil) cur = cur.getRight();
        return cur;
    }

    /* -------------------------
       Traversal helpers (recursive)
       ------------------------- */

    private void inorderRec(RBNode<T> node, List<T> out) {
        if (node == nil) return;
        inorderRec(node.getLeft(), out);
        out.add(node.getKey());
        inorderRec(node.getRight(), out);
    }

    private void preorderRec(RBNode<T> node, List<T> out) {
        if (node == nil) return;
        out.add(node.getKey());
        preorderRec(node.getLeft(), out);
        preorderRec(node.getRight(), out);
    }

    private void postorderRec(RBNode<T> node, List<T> out) {
        if (node == nil) return;
        postorderRec(node.getLeft(), out);
        postorderRec(node.getRight(), out);
        out.add(node.getKey());
    }

    private int heightRec(RBNode<T> node) {
        if (node == nil) return 0;
        return 1 + Math.max(heightRec(node.getLeft()), heightRec(node.getRight()));
    }
}