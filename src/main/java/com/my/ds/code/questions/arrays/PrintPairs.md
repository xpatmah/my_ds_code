Given a pile of n pairs of elements in array arr[], containing 2n elements, we have to print with the following conditions follow:

If pair exists of the elements then print it. Two elements form a pair if they have same value
Else do not print the unpaired elements
Examples:

Input: n = 3,

arr[] = {2, 1, 2, 3, 1, 3, 3, 2, 2}

Output:
Pair 1: 0 2
Pair 2: 1 4
Pair 3: 3 5
Pair 4: 7 8

Explanation: We have a total of 2n = 6 elements. We need to find all possible ways to partition these 6 elements into pairs and give the index of paired items.