package com.my.ds.code.sorting;

public class CocktailSort {
    // 11. Cocktail Shaker Sort (bidirectional bubble)
    public static void sort(int[] a) {
        int n = a.length;
        boolean swapped = true;
        int start = 0, end = n - 1;
        while (swapped) {
            swapped = false;
            for (int i = start; i < end; i++) {
                if (a[i] > a[i + 1]) { int t = a[i]; a[i] = a[i + 1]; a[i + 1] = t; swapped = true; }
            }
            if (!swapped) break;
            swapped = false;
            end--;
            for (int i = end - 1; i >= start; i--) {
                if (a[i] > a[i + 1]) { int t = a[i]; a[i] = a[i + 1]; a[i + 1] = t; swapped = true; }
            }
            start++;
        }
    }
}
