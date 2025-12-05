package com.my.ds.code.sorting;

public class ComboSort {

    // 12. Comb Sort
    public static void sort(int[] a) {
        int n = a.length;
        int gap = n;
        final double SHRINK = 1.3;
        boolean swapped = true;
        while (gap > 1 || swapped) {
            gap = (int) (gap / SHRINK);
            if (gap < 1) gap = 1;
            swapped = false;
            for (int i = 0; i + gap < n; i++) {
                if (a[i] > a[i + gap]) {
                    int t = a[i]; a[i] = a[i + gap]; a[i + gap] = t;
                    swapped = true;
                }
            }
        }
    }
}
