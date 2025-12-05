package com.my.ds.code.sorting;

public class MergeSort {
    // 5. Merge Sort
    public static void mergeSort(int[] a) {
        if (a == null || a.length <= 1) return;
        mergeSortRec(a, 0, a.length - 1);
    }
    private static void mergeSortRec(int[] a, int l, int r) {
        if (l >= r) return;
        int m = l + (r - l) / 2;
        mergeSortRec(a, l, m);
        mergeSortRec(a, m + 1, r);
        merge(a, l, m, r);
    }
    private static void merge(int[] a, int l, int m, int r) {
        int n1 = m - l + 1, n2 = r - m;
        int[] L = new int[n1], R = new int[n2];
        System.arraycopy(a, l, L, 0, n1);
        System.arraycopy(a, m + 1, R, 0, n2);
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) a[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        while (i < n1) a[k++] = L[i++];
        while (j < n2) a[k++] = R[j++];
    }
}
