package com.my.ds.code.dp.recursion.question1;

public class TowerOfHanoi {

    /**
     * Solves the Tower of Hanoi puzzle for a given number of disks.
     *
     * @param n The number of disks to move.
     * @param source The source peg (e.g., 'A').
     * @param auxiliary The auxiliary peg (e.g., 'B').
     * @param destination The destination peg (e.g., 'C').
     */
    public static void towerOfHanoi(int n, char source, char auxiliary, char destination) {
        // Base case: If there's only one disk, move it directly from source to destination.
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }

        // Step 1: Move n-1 disks from the source peg to the auxiliary peg,
        // using the destination peg as a temporary helper.
        towerOfHanoi(n - 1, source, destination, auxiliary);

        // Step 2: Move the largest (nth) disk from the source peg to the destination peg.
        System.out.println("Move disk " + n + " from " + source + " to " + destination);

        // Step 3: Move the n-1 disks from the auxiliary peg to the destination peg,
        // using the source peg as a temporary helper.
        towerOfHanoi(n - 1, auxiliary, source, destination);
    }

    public static void main(String[] args) {
        int numberOfDisks = 3; // Example: Solve for 3 disks
        System.out.println("Solving Tower of Hanoi for " + numberOfDisks + " disks:");
        towerOfHanoi(numberOfDisks, 'A', 'B', 'C'); // 'A' is source, 'B' is auxiliary, 'C' is destination
    }
}