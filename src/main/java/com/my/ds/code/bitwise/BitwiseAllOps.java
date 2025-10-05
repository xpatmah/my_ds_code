package com.my.ds.code.bitwise;

import java.util.Scanner;

public class BitwiseAllOps {

    // -----------------------
    // Basic bitwise operators demo
    // -----------------------
    public static void bitwiseOperators(int a, int b) {
        System.out.println("a & b = " + (a & b));
        System.out.println("a | b = " + (a | b));
        System.out.println("a ^ b = " + (a ^ b));
        System.out.println("~a = " + (~a));
        System.out.println("~b = " + (~b));
    }

    // -----------------------
    // Shifts
    // -----------------------
    public static void shifts(int a, int s) {
        System.out.println("a << s = " + (a << s));
        System.out.println("a >> s = " + (a >> s));
        System.out.println("a >>> s = " + (a >>> s));
    }

    // -----------------------
    // Get i-th bit (0-based)
    // -----------------------
    public static int getIthBit(int n, int i) {
        return (n >> i) & 1;
    }

    // -----------------------
    // Set i-th bit
    // -----------------------
    public static int setIthBit(int n, int i) {
        return n | (1 << i);
    }

    // -----------------------
    // Clear i-th bit
    // -----------------------
    public static int clearIthBit(int n, int i) {
        int mask = ~(1 << i);
        return n & mask;
    }

    // -----------------------
    // Update i-th bit to value (0 or 1)
    // -----------------------
    public static int updateIthBit(int n, int i, int bitIs) {
        // clear ith bit then set if bitIs == 1
        int mask = ~(1 << i);
        n = n & mask;
        return n | (bitIs << i);
    }

    // -----------------------
    // Clear last i bits (i least significant bits)
    // -----------------------
    public static int clearLastIBits(int n, int i) {
        int mask = -1 << i; // (~0) << i
        return n & mask;
    }

    // -----------------------
    // Replace bits i..j in N with M
    // (Assume 0 <= i <= j)
    // -----------------------
    public static int replaceBits(int N, int M, int i, int j) {
        // Mask to clear bits i through j in N
        int allOnes = ~0; // sequence of 1s
        int left = allOnes << (j + 1); // 1's before position j
        int right = (1 << i) - 1; // 1's after position i-1
        int mask = left | right; // 1s, with zeros between i and j

        // Clear bits j through i, then put M in there
        int n_cleared = N & mask;
        int m_shifted = (M << i) & ~left & ~right; // or simply (M << i)
        return n_cleared | (M << i);
    }

    // -----------------------
    // Two Power: check if n is power of two
    // -----------------------
    public static boolean isPowerOfTwo(long n) {
        return n > 0 && ( (n & (n - 1)) == 0 );
    }

    // -----------------------
    // Counting set bits (naive)
    // -----------------------
    public static int countSetBits(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n >>>= 1; // unsigned shift to handle sign bits for negative? but for int it's fine
        }
        return count;
    }

    // -----------------------
    // Counting bits hack (Brian Kernighan)
    // -----------------------
    public static int countSetBitsBK(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    // -----------------------
    // Fast exponentiation (integer)
    // computes base^exp for non-negative exp
    // returns long
    // -----------------------
    public static long fastPow(long base, long exp) {
        long res = 1;
        long cur = base;
        long e = exp;
        while (e > 0) {
            if ((e & 1) == 1) res = res * cur;
            cur = cur * cur;
            e >>= 1;
        }
        return res;
    }

    // Fast exponentiation for double base and non-negative integer exp
    public static double fastPowDouble(double base, long exp) {
        double res = 1.0;
        double cur = base;
        long e = exp;
        while (e > 0) {
            if ((e & 1) == 1) res *= cur;
            cur *= cur;
            e >>= 1;
        }
        return res;
    }

    // -----------------------
    // Decimal to Binary (custom)
    // returns binary string of absolute value and two's complement for negative
    // -----------------------
    public static String decimalToBinary(int n) {
        if (n == 0) return "0";
        if (n > 0) {
            StringBuilder sb = new StringBuilder();
            int x = n;
            while (x > 0) {
                sb.append(x & 1);
                x >>= 1;
            }
            return sb.reverse().toString();
        } else {
            // For negative, show 32-bit two's complement representation
            return Integer.toBinaryString(n); // Java shows two's complement for negative ints
        }
    }

    // -----------------------
    // Helper to print binary padded to 32 bits
    // -----------------------
    public static String to32BitBinary(int n) {
        String s = Integer.toBinaryString(n);
        if (s.length() < 32) {
            StringBuilder sb = new StringBuilder();
            for (int i = s.length(); i < 32; i++) sb.append('0');
            sb.append(s);
            return sb.toString();
        } else if (s.length() > 32) {
            return s.substring(s.length() - 32);
        } else return s;
    }

    // -----------------------
    // Demo run for all operations with sample values
    // -----------------------
    public static void demoAll() {
        int a = 29; // 11101
        int b = 15; // 01111
        System.out.println("Demo values: a = " + a + ", b = " + b);
        System.out.println("Binary a: " + to32BitBinary(a));
        System.out.println("Binary b: " + to32BitBinary(b));
        System.out.println("\n-- Bitwise operators --");
        bitwiseOperators(a, b);

        System.out.println("\n-- Shifts --");
        shifts(a, 2);

        System.out.println("\n-- Get/Set/Clear/Update ith Bit --");
        int i = 3;
        System.out.println("getIthBit(a, " + i + ") = " + getIthBit(a, i));
        System.out.println("setIthBit(a, " + i + ") = " + setIthBit(a, i) + " -> " + to32BitBinary(setIthBit(a,i)));
        System.out.println("clearIthBit(a, " + i + ") = " + clearIthBit(a, i) + " -> " + to32BitBinary(clearIthBit(a,i)));
        System.out.println("updateIthBit(a, " + i + ", 1) = " + updateIthBit(a, i, 1));

        System.out.println("\n-- Clear last i bits --");
        System.out.println("clearLastIBits(a, 3) = " + clearLastIBits(a, 3));

        System.out.println("\n-- Replace bits --");
        int N = 1024; // 100 0000 0000
        int M = 19;   //      10011
        int ii = 2, jj = 6;
        System.out.println("replaceBits(" + N + ", " + M + ", " + ii + ", " + jj + ") = " + replaceBits(N, M, ii, jj));

        System.out.println("\n-- Two Power checks --");
        System.out.println("isPowerOfTwo(16): " + isPowerOfTwo(16));
        System.out.println("isPowerOfTwo(18): " + isPowerOfTwo(18));

        System.out.println("\n-- Counting set bits --");
        System.out.println("countSetBits(a) = " + countSetBits(a));
        System.out.println("countSetBitsBK(a) = " + countSetBitsBK(a));

        System.out.println("\n-- Fast exponentiation --");
        System.out.println("fastPow(3, 13) = " + fastPow(3, 13));
        System.out.println("fastPowDouble(2.5, 10) = " + fastPowDouble(2.5, 10));

        System.out.println("\n-- Decimal to Binary --");
        System.out.println("decimalToBinary(29) = " + decimalToBinary(29));
        System.out.println("decimalToBinary(-5) = " + decimalToBinary(-5));
    }

    // -----------------------
    // Menu-driven CLI
    // -----------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Bitwise Operations Menu ===");
            System.out.println("1. Bitwise operators (&,|,^,~)");
            System.out.println("2. Left/Right/Unsigned shifts");
            System.out.println("3. Get i-th bit");
            System.out.println("4. Set i-th bit");
            System.out.println("5. Clear i-th bit");
            System.out.println("6. Update i-th bit");
            System.out.println("7. Clear last i bits");
            System.out.println("8. Replace bits (N, M, i, j)");
            System.out.println("9. Check power of two");
            System.out.println("10. Count set bits (naive & BK)");
            System.out.println("11. Fast exponentiation (integer)");
            System.out.println("12. Decimal to Binary");
            System.out.println("13. Run demo (sample run for all)");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int opt;
            try {
                opt = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid input, try again.");
                continue;
            }

            if (opt == 0) {
                System.out.println("Bye!");
                break;
            }

            try {
                switch (opt) {
                    case 1: {
                        System.out.print("Enter a: ");
                        int a = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter b: ");
                        int b = Integer.parseInt(sc.nextLine().trim());
                        bitwiseOperators(a, b);
                        break;
                    }
                    case 2: {
                        System.out.print("Enter a: ");
                        int a = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter shift amount s (>=0): ");
                        int s = Integer.parseInt(sc.nextLine().trim());
                        shifts(a, s);
                        break;
                    }
                    case 3: {
                        System.out.print("Enter n: ");
                        int n = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter i (0-based): ");
                        int i = Integer.parseInt(sc.nextLine().trim());
                        System.out.println("getIthBit = " + getIthBit(n, i));
                        break;
                    }
                    case 4: {
                        System.out.print("Enter n: ");
                        int n = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter i (0-based): ");
                        int i = Integer.parseInt(sc.nextLine().trim());
                        int res = setIthBit(n, i);
                        System.out.println("Result: " + res + " (bin: " + to32BitBinary(res) + ")");
                        break;
                    }
                    case 5: {
                        System.out.print("Enter n: ");
                        int n = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter i (0-based): ");
                        int i = Integer.parseInt(sc.nextLine().trim());
                        int res = clearIthBit(n, i);
                        System.out.println("Result: " + res + " (bin: " + to32BitBinary(res) + ")");
                        break;
                    }
                    case 6: {
                        System.out.print("Enter n: ");
                        int n = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter i (0-based): ");
                        int i = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter bit value (0 or 1): ");
                        int bit = Integer.parseInt(sc.nextLine().trim());
                        int res = updateIthBit(n, i, bit == 0 ? 0 : 1);
                        System.out.println("Result: " + res + " (bin: " + to32BitBinary(res) + ")");
                        break;
                    }
                    case 7: {
                        System.out.print("Enter n: ");
                        int n = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter i (clear last i bits): ");
                        int i = Integer.parseInt(sc.nextLine().trim());
                        int res = clearLastIBits(n, i);
                        System.out.println("Result: " + res + " (bin: " + to32BitBinary(res) + ")");
                        break;
                    }
                    case 8: {
                        System.out.print("Enter N: ");
                        int N = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter M: ");
                        int M = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter i (start): ");
                        int ii = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter j (end): ");
                        int jj = Integer.parseInt(sc.nextLine().trim());
                        int res = replaceBits(N, M, ii, jj);
                        System.out.println("Result: " + res + " (bin: " + to32BitBinary(res) + ")");
                        break;
                    }
                    case 9: {
                        System.out.print("Enter n (long): ");
                        long n2 = Long.parseLong(sc.nextLine().trim());
                        System.out.println("isPowerOfTwo: " + isPowerOfTwo(n2));
                        break;
                    }
                    case 10: {
                        System.out.print("Enter n: ");
                        int n = Integer.parseInt(sc.nextLine().trim());
                        System.out.println("countSetBits (naive) = " + countSetBits(n));
                        System.out.println("countSetBitsBK = " + countSetBitsBK(n));
                        break;
                    }
                    case 11: {
                        System.out.print("Enter base (long): ");
                        long base = Long.parseLong(sc.nextLine().trim());
                        System.out.print("Enter exponent (non-negative long): ");
                        long exp = Long.parseLong(sc.nextLine().trim());
                        System.out.println("fastPow = " + fastPow(base, exp));
                        break;
                    }
                    case 12: {
                        System.out.print("Enter n (int): ");
                        int n = Integer.parseInt(sc.nextLine().trim());
                        System.out.println("Binary: " + decimalToBinary(n));
                        break;
                    }
                    case 13: {
                        demoAll();
                        break;
                    }
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

        sc.close();
    }
}
