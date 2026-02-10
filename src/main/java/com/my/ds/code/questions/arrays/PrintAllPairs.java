package com.my.ds.code.questions.arrays;

public class PrintAllPairs {

    /**
     * Prints all possible unordered pairs of elements from an array.
     * @param arr The input array of integers.
     */
    public static void printPairs(int[] arr) {
        System.out.println("All possible pairs from the array:");
        
        // Outer loop to select the first element of the pair
        for (int i = 0; i < arr.length; i++) {
            // Inner loop to select the second element (from i+1 to avoid duplicates and self-pairing)
            for (int j = i + 1; j < arr.length; j++) {
                System.out.println("(" + arr[i] + ", " + arr[j] + ")");
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {2, 4, 6, 8};
        printPairs(numbers);
    }
}