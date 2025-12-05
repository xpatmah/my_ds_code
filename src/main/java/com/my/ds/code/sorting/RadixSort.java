package com.my.ds.code.sorting;

public class RadixSort {

    // 9. Radix Sort (LSD base 10) for non-negative ints
    public static void sort(int[] a) {
        if (a.length == 0) return;
        int max = a[0];
        for (int v : a) if (v > max) max = v;
        for (int exp = 1; max / exp > 0; exp *= 10) countingSortByDigit(a, exp);
    }
    private static void countingSortByDigit(int[] a, int exp) {
        int n = a.length;
        int[] output = new int[n];
        int[] count = new int[10];
        for (int i = 0; i < n; i++) count[(a[i] / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            int d = (a[i] / exp) % 10;
            output[count[d] - 1] = a[i];
            count[d]--;
        }
        System.arraycopy(output, 0, a, 0, n);
    }

}
