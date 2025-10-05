📘 Overview

Exponential Search (also known as Exponential Binary Search) is an efficient searching algorithm designed for sorted arrays.

It works by finding a range where the element is likely to exist using exponential jumps, and then applying Binary Search within that range.

It is especially useful for unbounded or infinite lists, where the array size is unknown.

⚙️ How It Works

Start with the first element.

If it matches the key, return its index.

Exponentially increase the index by powers of 2:

Compare the element at index 2^i with the key.

Continue until arr[2^i] is greater than the key or exceeds array length.

Once the range is found ([2^(i-1), 2^i]), perform Binary Search within this range.

🧩 Algorithm Steps

Initialize i = 1.

If arr[0] == key, return 0.

While i < n and arr[i] <= key, set i = i * 2.

Perform Binary Search on the subarray from i/2 to min(i, n-1).

Return the index if found, otherwise -1.