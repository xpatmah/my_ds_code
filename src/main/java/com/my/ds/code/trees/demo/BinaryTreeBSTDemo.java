package com.my.ds.code.trees.demo;

import com.my.ds.code.trees.impl.BinarySearchTree;
import com.my.ds.code.trees.impl.BinaryTree;

public class BinaryTreeBSTDemo {
    public static void main(String[] args) {
        System.out.println("=== BinaryTree (general) demo ===");
        BinaryTree bt = new BinaryTree();
        int[] values = { 1, 2, 3, 4, 5, 6, 7 };
        for (int v : values) bt.insertLevelOrder(v);

        System.out.print("Inorder (recursive): ");
        bt.inorderRecursive(bt.getRoot());
        System.out.println();

        System.out.print("Preorder (recursive): ");
        bt.preorderRecursive(bt.getRoot());
        System.out.println();

        System.out.print("Postorder (recursive): ");
        bt.postorderRecursive(bt.getRoot());
        System.out.println();

        System.out.println("Inorder (iterative): " + bt.inorderIterative());
        System.out.println("Preorder (iterative): " + bt.preorderIterative());
        System.out.println("Postorder (iterative): " + bt.postorderIterative());
        System.out.println("Level-order: " + bt.levelOrder());
        System.out.println("Size: " + bt.size());
        System.out.println("Height: " + bt.height());

        String ser = bt.serialize();
        System.out.println("Serialized: " + ser);

        BinaryTree bt2 = new BinaryTree();
        bt2.deserialize(ser);
        System.out.println("Deserialized level-order: " + bt2.levelOrder());

        System.out.println("\n=== BinarySearchTree demo ===");
        BinarySearchTree bst = new BinarySearchTree();
        int[] bstVals = {50, 30, 70, 20, 40, 60, 80};
        for (int v : bstVals) bst.insert(v);

        System.out.print("Inorder (recursive): ");
        bst.inorderRecursive(bst.getRoot());
        System.out.println();

        System.out.println("Inorder (iterative): " + bst.inorderIterative());
        System.out.println("Preorder (iterative): " + bst.preorderIterative());
        System.out.println("Postorder (iterative): " + bst.postorderIterative());
        System.out.println("Level-order: " + bst.levelOrder());

        System.out.println("Search 40: " + bst.search(40));
        System.out.println("Search 100: " + bst.search(100));

        System.out.println("Min: " + bst.findMin() + ", Max: " + bst.findMax());
        System.out.println("Size: " + bst.size() + ", Height: " + bst.height());
        System.out.println("Is valid BST: " + bst.isValidBST());

        System.out.println("Kth smallest (k=3): " + bst.kthSmallest(3));

        System.out.println("\nDelete 70");
        bst.delete(70);
        System.out.println("Level-order after delete: " + bst.levelOrder());

        System.out.println("\nSerialize BST: " + bst.serialize());
        BinarySearchTree bst3 = new BinarySearchTree();
        bst3.deserialize(bst.serialize());
        System.out.println("Deserialized BST level-order: " + bst3.levelOrder());
    }
}