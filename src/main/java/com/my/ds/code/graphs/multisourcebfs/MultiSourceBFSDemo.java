package com.my.ds.code.graphs.multisourcebfs;

import java.util.*;

/**
 * Multi-Source BFS implementation
 * Finds minimum distance from nearest source (value = 1)
 */
public class MultiSourceBFSDemo {

    // Directions: up, down, left, right
    private static final int[][] DIRS = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    // ============================
    // Multi-Source BFS Method
    // ============================
    public static int[][] multiSourceBFS(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] distance = new int[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        // Step 1: Initialize distances
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    distance[i][j] = 0;           // source
                    queue.offer(new int[]{i, j});
                } else {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // Step 2: BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];

            for (int[] d : DIRS) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nc >= 0 &&
                    nr < rows && nc < cols &&
                    distance[nr][nc] > distance[r][c] + 1) {

                    distance[nr][nc] = distance[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return distance;
    }

    // ============================
    // Utility to print matrix
    // ============================
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // ============================
    // MAIN METHOD
    // ============================
    public static void main(String[] args) {

        int[][] grid = {
            {0, 0, 1},
            {0, 0, 0},
            {1, 0, 0}
        };

        System.out.println("Input Grid:");
        printMatrix(grid);

        int[][] result = multiSourceBFS(grid);

        System.out.println("Distance from nearest source:");
        printMatrix(result);
    }
}