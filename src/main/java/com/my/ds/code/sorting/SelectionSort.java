package com.my.ds.code.sorting;

public class SelectionSort {
    // 2. Selection Sort
    public static void selectionSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) if (a[j] < a[minIdx]) minIdx = j;
            int t = a[i]; a[i] = a[minIdx]; a[minIdx] = t;
        }
    }
}
