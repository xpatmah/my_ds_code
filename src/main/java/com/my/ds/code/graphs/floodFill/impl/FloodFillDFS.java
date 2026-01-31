package com.my.ds.code.graphs.floodFill.impl;

public class FloodFillDFS {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image;

        dfs(image, sr, sc, oldColor, newColor);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int oldColor, int newColor) {
        // Boundary check
        if (r < 0 || c < 0 || r >= image.length || c >= image[0].length)
            return;

        // Color mismatch
        if (image[r][c] != oldColor)
            return;

        // Fill color
        image[r][c] = newColor;

        // 4-directional DFS
        dfs(image, r + 1, c, oldColor, newColor);
        dfs(image, r - 1, c, oldColor, newColor);
        dfs(image, r, c + 1, oldColor, newColor);
        dfs(image, r, c - 1, oldColor, newColor);
    }
}