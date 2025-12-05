package com.my.ds.code.sorting;

public class QuickSort {

    // 6. Quick Sort (Lomuto partition)
    public static void quickSort(int[] a) {
        quickSortRec(a, 0, a.length - 1);
    }

    private static void quickSortRec(int[] a, int lo, int hi) {
        if (lo < hi) {
            int p = lomutoPartition(a, lo, hi);
            quickSortRec(a, lo, p - 1);
            quickSortRec(a, p + 1, hi);
        }
    }
    private static int lomutoPartition(int[] a, int lo, int hi) {
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (a[j] < pivot) {
                int t = a[i]; a[i] = a[j]; a[j] = t;
                i++;
            }
        }
        int t = a[i]; a[i] = a[hi]; a[hi] = t;
        return i;
    }

}
