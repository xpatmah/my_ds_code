package com.my.ds.code.sorting;

public class HeapSort {

    // 7. Heap Sort
    public static void heapSort(int[] a) {
        int n = a.length;
        // build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(a, n, i);
        }
        // extract elements
        for (int i = n - 1; i > 0; i--) {
            int tmp = a[0]; a[0] = a[i]; a[i] = tmp;
            heapify(a, i, 0);
        }
    }
    private static void heapify(int[] a, int heapSize, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        if (left < heapSize && a[left] > a[largest]) largest = left;
        if (right < heapSize && a[right] > a[largest]) largest = right;
        if (largest != root) {
            int t = a[root]; a[root] = a[largest]; a[largest] = t;
            heapify(a, heapSize, largest);
        }
    }
}
