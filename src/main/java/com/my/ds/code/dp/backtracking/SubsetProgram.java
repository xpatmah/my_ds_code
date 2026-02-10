package com.my.ds.code.dp.backtracking;

import java.util.*;

public class SubsetProgram {

    static void findSubsets(int[] arr, int index,
                            List<Integer> current,
                            List<List<Integer>> result) {

        // base case
        if (index == arr.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // exclude current element
        findSubsets(arr, index + 1, current, result);

        // include current element
        current.add(arr[index]);
        findSubsets(arr, index + 1, current, result);

        // backtrack
        current.remove(current.size() - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();

        findSubsets(arr, 0, new ArrayList<>(), result);

        System.out.println(result);
    }
}