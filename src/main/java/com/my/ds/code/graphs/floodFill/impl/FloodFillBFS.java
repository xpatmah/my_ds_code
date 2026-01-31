package com.my.ds.code.graphs.floodFill.impl;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFillBFS {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int nr = cell[0] + d[0];
                int nc = cell[1] + d[1];

                if (nr >= 0 && nc >= 0 &&
                    nr < image.length && nc < image[0].length &&
                    image[nr][nc] == oldColor) {

                    image[nr][nc] = newColor;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return image;
    }
}