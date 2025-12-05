package com.my.ds.code.trees.demo;

import com.my.ds.code.trees.impl.AVLTree;

public class AvlTreeDemo {

    // ---------- Demo / test ----------
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        System.out.println("Inserting: 10,20,30,40,50,25");
        avl.insert(10);
        avl.insert(20);
        avl.insert(30);
        avl.insert(40);
        avl.insert(50);
        avl.insert(25);

        System.out.println("Inorder (sorted): " + avl.inorder());
        System.out.println("Preorder: " + avl.preorder());
        System.out.println("Postorder: " + avl.postorder());
        System.out.println("Level-order: " + avl.levelOrder());
        System.out.println("Size: " + avl.size() + ", Height: " + avl.height());
        System.out.println("Min: " + avl.findMin() + ", Max: " + avl.findMax());

        System.out.println("\nSearch 25: " + avl.search(25));
        System.out.println("Search 15: " + avl.search(15));

        System.out.println("\nDelete 40");
        avl.delete(40);
        System.out.println("Inorder: " + avl.inorder());
        System.out.println("Level-order: " + avl.levelOrder());
        System.out.println("Size: " + avl.size() + ", Height: " + avl.height());

        System.out.println("\nDelete 10");
        avl.delete(10);
        System.out.println("Inorder: " + avl.inorder());
        System.out.println("Level-order: " + avl.levelOrder());
        System.out.println("Size: " + avl.size() + ", Height: " + avl.height());

        System.out.println("\nClearing tree");
        avl.clear();
        System.out.println("Size after clear: " + avl.size() + ", Height: " + avl.height());
    }
}
