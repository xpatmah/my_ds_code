package com.my.ds.code.sorting;

public class BubbleSort {

    // 1. Bubble Sort (notmal)
    public static void bubbleSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t;
                }
            }
        }
    }

    // 1. Bubble Sort (optimized)
    public static void bubbleSortOptimized(int[] a) {
        int n = a.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // 1. Bubble Sort (Recursive)
    public static void bubbleSortRec(int[] a , int n , int j ) {
        if(n==1){
            return;
        }
        if (j==n-1) {
            bubbleSortRec(a, n - 1, 0);
            return;
        }

        if (a[j] > a[j + 1]) {
            int t = a[j];
            a[j] = a[j + 1];
            a[j + 1] = t;
        }
        bubbleSortRec(a , n , j+1);
    }
}
