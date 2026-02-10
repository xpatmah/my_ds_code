package com.my.ds.code.dp.backtracking;

import java.util.*;

public class GenerateBrackets {

    public static void generate(int open, int close, int n,
                                String current, List<String> result) {

        // base case
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        // add opening bracket
        if (open < n) {
            generate(open + 1, close, n, current + "(", result);
        }

        // add closing bracket
        if (close < open) {
            generate(open, close + 1, n, current + ")", result);
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(0, 0, n, "", result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}