package com.my.ds.code.trees.demo;

import com.my.ds.code.trees.impl.RedBlackTree;

public class RedBlackTreeDemo {

    /* -------------------------
       Demo / testing
       ------------------------- */

    public static void main(String[] args) {
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        System.out.println("Inserting: 10, 20, 30, 15, 25, 5, 1, 8, 12");
        int[] ins = {10,20,30,15,25,5,1,8,12};
        for (int v : ins) rbt.insert(v);

        System.out.println("Inorder (should be sorted): " + rbt.inorder());
        System.out.println("Preorder: " + rbt.preorder());
        System.out.println("Postorder: " + rbt.postorder());
        System.out.println("Level-order: " + rbt.levelOrder());
        System.out.println("Size: " + rbt.size());
        System.out.println("Height (levels): " + rbt.height());
        System.out.println("Min: " + rbt.findMin() + ", Max: " + rbt.findMax());
        System.out.println("Search 15: " + rbt.search(15) + ", Search 99: " + rbt.search(99));

        System.out.println("\nDelete 20");
        rbt.delete(20);
        System.out.println("Inorder after delete 20: " + rbt.inorder());
        System.out.println("Level-order after delete 20: " + rbt.levelOrder());
        System.out.println("Size: " + rbt.size() + ", Height: " + rbt.height());

        System.out.println("\nDelete 10");
        rbt.delete(10);
        System.out.println("Inorder after delete 10: " + rbt.inorder());
        System.out.println("Level-order after delete 10: " + rbt.levelOrder());
        System.out.println("Size: " + rbt.size() + ", Height: " + rbt.height());

        System.out.println("\nClearing tree");
        rbt.clear();
        System.out.println("Size after clear: " + rbt.size() + ", Height: " + rbt.height());
    }
}
