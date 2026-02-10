package com.my.ds.code.dp.recursion;

public class FriendsPairing {

    public static long countWays(int n) {
        if (n <= 1) return 1;

        long prev2 = 1; // dp[0]
        long prev1 = 1; // dp[1]

        for (int i = 2; i <= n; i++) {
            long curr = prev1 + (i - 1) * prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    public static void main(String[] args) {
        System.out.println(countWays(6)); // 76
    }
}