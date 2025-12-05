package com.my.ds.code.trees.domain;

public class TreeNode {
    private int data;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // getters
    public int getData() { return data; }
    public TreeNode getLeft() { return left; }
    public TreeNode getRight() { return right; }

    // setters
    public void setData(int data) { this.data = data; }
    public void setLeft(TreeNode left) { this.left = left; }
    public void setRight(TreeNode right) { this.right = right; }
}