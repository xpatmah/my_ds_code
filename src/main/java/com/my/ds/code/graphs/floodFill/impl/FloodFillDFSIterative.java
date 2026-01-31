package com.my.ds.code.graphs.floodFill.impl;

import java.util.Stack;

public class FloodFillDFSIterative {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image;

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{sr, sc});

        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            int r = cell[0], c = cell[1];

            if (r < 0 || c < 0 || r >= image.length || c >= image[0].length)
                continue;
            if (image[r][c] != oldColor)
                continue;

            image[r][c] = newColor;

            stack.push(new int[]{r + 1, c});
            stack.push(new int[]{r - 1, c});
            stack.push(new int[]{r, c + 1});
            stack.push(new int[]{r, c - 1});
        }
        return image;
    }
}