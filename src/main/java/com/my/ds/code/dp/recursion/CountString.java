package com.my.ds.code.dp.recursion;

import java.util.ArrayList;
import java.util.List;

public class CountString {

    static void stringRecur(int i, StringBuilder s, List<String> ans) {

        // Base case: If we've filled all positions,
        // add the string to results
        if (i >= s.length()) {
            ans.add(s.toString());
            return;
        }

        // Case 1: Keep the current position as
        // '0' and move to next position
        stringRecur(i + 1, s, ans);

        // Case 2: Try placing '1' at current position.
        // Skip the next position when we place a '1'
        // to avoid consecutive 1's
        s.setCharAt(i, '1');

        // Skip next position to avoid consecutive 1's
        stringRecur(i + 2, s, ans);

        // Backtrack: Restore the current position back to '0'
        s.setCharAt(i, '0');
    }

    static List<String> generateBinaryStrings(int n) {

        // Initialize a string of n zeros
        // as our starting point
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < n; j++) {
            s.append('0');
        }

        List<String> ans = new ArrayList<>();

        stringRecur(0, s, ans);

        return ans;
    }

    public static void main(String[] args) {
        int n = 4;
        List<String> res = generateBinaryStrings(n);
        for (String s : res) {
            System.out.println(s);
        }
    }

}
