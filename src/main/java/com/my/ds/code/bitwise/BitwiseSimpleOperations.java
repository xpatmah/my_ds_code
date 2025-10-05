package com.my.ds.code.bitwise;

import java.util.Scanner;

public class BitwiseSimpleOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number (a): ");
        int a = sc.nextInt();

        System.out.print("Enter second number (b): ");
        int b = sc.nextInt();

        System.out.println("\n=== Bitwise Operations Demo ===");

        // Bitwise AND
        System.out.println("a & b = " + (a & b));

        // Bitwise OR
        System.out.println("a | b = " + (a | b));

        // Bitwise XOR
        System.out.println("a ^ b = " + (a ^ b));

        // Bitwise NOT
        System.out.println("~a = " + (~a));
        System.out.println("~b = " + (~b));

        // Left Shift
        System.out.println("a << 1 = " + (a << 1));
        System.out.println("b << 1 = " + (b << 1));

        // Right Shift (sign-propagating)
        System.out.println("a >> 1 = " + (a >> 1));
        System.out.println("b >> 1 = " + (b >> 1));

        // Unsigned Right Shift (zero-fill)
        System.out.println("a >>> 1 = " + (a >>> 1));
        System.out.println("b >>> 1 = " + (b >>> 1));

        // Some bitwise tricks
        System.out.println("\n=== Bitwise Tricks ===");
        System.out.println(a + " is " + ((a & 1) == 0 ? "Even" : "Odd"));
        System.out.println(b + " is " + ((b & 1) == 0 ? "Even" : "Odd"));

        // Swap using XOR
        int x = a, y = b;
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println("After XOR Swap: a = " + x + ", b = " + y);

        // Power of 2 check
        boolean isPowerA = (a > 0) && ((a & (a - 1)) == 0);
        boolean isPowerB = (b > 0) && ((b & (b - 1)) == 0);
        System.out.println(a + " is power of 2? " + isPowerA);
        System.out.println(b + " is power of 2? " + isPowerB);

        sc.close();
    }
}