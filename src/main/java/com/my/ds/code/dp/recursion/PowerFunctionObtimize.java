package com.my.ds.code.dp.recursion;

public class PowerFunctionObtimize {

    public static double power(double x, int n) {
        if (n == 0) return 1;

        double half = power(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        } else {
            return x * half * half;
        }
    }

    public static void main(String[] args) {
        System.out.println(power(2, 10)); // 1024.0
    }
}