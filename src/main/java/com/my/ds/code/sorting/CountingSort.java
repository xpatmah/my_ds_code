package com.my.ds.code.sorting;

public class CountingSort {
    // 8. Counting Sort (non-negative ints)
    public static void sort(int[] a) {
        if (a.length == 0) return;
        int max = a[0];
        for (int v : a) if (v > max) max = v;
        int[] count = new int[max + 1];
        for (int v : a) count[v]++;
        int idx = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0) a[idx++] = i;
        }
    }
}
