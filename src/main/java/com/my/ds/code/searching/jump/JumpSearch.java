package com.my.ds.code.searching.jump;

public class JumpSearch {
    public static int jumpSearch(int[] arr, int key) {
        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;

        while (arr[Math.min(step, n) - 1] < key) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) return -1;
        }

        while (arr[prev] < key) {
            prev++;
            if (prev == Math.min(step, n)) return -1;
        }

        if (arr[prev] == key)
            return prev;
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80};
        int key = 70;
        int index = jumpSearch(arr, key);
        System.out.println(index != -1 ? "Found at index " + index : "Not found");
    }
}
