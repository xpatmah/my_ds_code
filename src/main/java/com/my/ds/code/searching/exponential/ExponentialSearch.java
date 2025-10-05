package com.my.ds.code.searching.exponential;

public class ExponentialSearch {
    public static int exponentialSearch(int[] arr, int key) {
        int n = arr.length;
        if (arr[0] == key) return 0;

        int i = 1;
        while (i < n && arr[i] <= key)
            i *= 2;

        return binarySearch(arr, i / 2, Math.min(i, n - 1), key);
    }

    private static int binarySearch(int[] arr, int left, int right, int key) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key)
                return mid;
            if (arr[mid] < key)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {5, 10, 15, 20, 25, 30, 35, 40};
        int key = 30;
        int index = exponentialSearch(arr, key);
        System.out.println(index != -1 ? "Found at index " + index : "Not found");
    }
}