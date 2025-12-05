package com.my.ds.code.sorting;

import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {

    // 10. Bucket Sort (for double values in [0,1))
    public static void sort(double[] a) {
        int n = a.length;
        if (n == 0) return;
        ArrayList<Double>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) buckets[i] = new ArrayList<>();
        for (double v : a) {
            int idx = (int) (v * n);
            buckets[Math.min(idx, n - 1)].add(v);
        }
        for (ArrayList<Double> b : buckets) Collections.sort(b);
        int pos = 0;
        for (ArrayList<Double> b : buckets) for (double v : b) a[pos++] = v;
    }
}
