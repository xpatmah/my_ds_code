package com.my.ds.code.graphs.floodFill.demo;

import com.my.ds.code.graphs.floodFill.impl.FloodFillBFS;
import com.my.ds.code.graphs.floodFill.impl.FloodFillDFS;

public class FloodFillDemo {

    // =========================
    // MAIN METHOD
    // =========================
    public static void main(String[] args) {

        FloodFillBFS floodFillBfsCode = new FloodFillBFS();

        FloodFillDFS floodFillDfs = new FloodFillDFS();

        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        int sr = 1, sc = 1, newColor = 2;

        System.out.println("Original Image:");
        printImage(image);

        // DFS Flood Fill
        int[][] dfsResult = floodFillDfs.floodFill(copy(image), sr, sc, newColor);
        System.out.println("After Flood Fill (DFS):");
        printImage(dfsResult);

        // BFS Flood Fill
        int[][] bfsResult = floodFillBfsCode.floodFill(copy(image), sr, sc, newColor);
        System.out.println("After Flood Fill (BFS):");
        printImage(bfsResult);
    }

    // Utility to deep copy 2D array
    private static int[][] copy(int[][] image) {
        int[][] copy = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            copy[i] = image[i].clone();
        }
        return copy;
    }

    // =========================
    // Utility: Print Matrix
    // =========================
    private static void printImage(int[][] image) {
        for (int[] row : image) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
