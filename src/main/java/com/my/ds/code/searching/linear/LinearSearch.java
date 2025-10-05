package com.my.ds.code.searching.linear;

public class LinearSearch {
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key)
                return i; // return index if found
        }
        return -1; // not found
    }

    public static void main(String[] args) {
        int[] arr = {10, 25, 30, 45, 50};
        int key = 30;
        int index = linearSearch(arr, key);
        System.out.println(index != -1 ? "Found at index " + index : "Not found");
    }
}