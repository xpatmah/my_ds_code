package com.my.ds.code.dp.recursion;

public class TowerOfHanoi {

    public static void solve(int n, char source, char auxiliary, char destination) {
        if (n == 0) return;

        // move n-1 disks to auxiliary
        solve(n - 1, source, destination, auxiliary);

        // move largest disk
        System.out.println("Move disk " + n + " from " + source + " to " + destination);

        // move n-1 disks to destination
        solve(n - 1, auxiliary, source, destination);
    }

    public static void main(String[] args) {
        int n = 3;
        solve(n, 'A', 'B', 'C');
    }
}
