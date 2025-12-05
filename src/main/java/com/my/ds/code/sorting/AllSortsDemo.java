package com.my.ds.code.sorting;// AllSorts.java
// Compile: javac AllSorts.java
// Run:     java AllSorts

import java.util.Arrays;

public class AllSortsDemo {

    // Demo runner
    public static void main(String[] args) {
        int[] sample = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original: " + Arrays.toString(sample));

        int[] a;

        a = AllSortsUtils.copy(sample); BubbleSort.bubbleSort(a); System.out.println("Bubble:   " + Arrays.toString(a));
        a = AllSortsUtils.copy(sample); SelectionSort.selectionSort(a); System.out.println("Selection:" + Arrays.toString(a));
        a = AllSortsUtils.copy(sample); InsertionSort.insertionSort(a); System.out.println("Insertion:" + Arrays.toString(a));
        a = AllSortsUtils.copy(sample); ShellSort.shellSort(a); System.out.println("Shell:    " + Arrays.toString(a));
        a = AllSortsUtils.copy(sample); MergeSort.mergeSort(a); System.out.println("Merge:    " + Arrays.toString(a));
        a = AllSortsUtils.copy(sample); QuickSort.quickSort(a); System.out.println("Quick:    " + Arrays.toString(a));
        a = AllSortsUtils.copy(sample); HeapSort.heapSort(a); System.out.println("Heap:     " + Arrays.toString(a));
        a = AllSortsUtils.copy(sample); CocktailSort.sort(a); System.out.println("Cocktail: " + Arrays.toString(a));
        a = AllSortsUtils.copy(sample); ComboSort.sort(a); System.out.println("Comb:     " + Arrays.toString(a));

        // Counting / Radix require non-negative ints
        int[] nonNeg = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("\nNon-neg sample: " + Arrays.toString(nonNeg));
        int[] b = AllSortsUtils.copy(nonNeg); CountingSort.sort(b); System.out.println("Counting: " + Arrays.toString(b));
        b = AllSortsUtils.copy(nonNeg); RadixSort.sort(b); System.out.println("Radix:    " + Arrays.toString(b));

        double[] dbl = {0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68};
        System.out.println("\nDoubles sample: " + Arrays.toString(dbl));
        BucketSort.sort(dbl); System.out.println("Bucket:   " + Arrays.toString(dbl));
    }
}
