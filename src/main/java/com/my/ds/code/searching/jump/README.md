📘 Overview

Jump Search is an algorithm for searching sorted arrays.
It works by jumping ahead by fixed steps instead of checking every element like Linear Search, and then performing a linear search within the block where the element could be.

It’s a hybrid of Linear Search and Binary Search — faster than Linear Search but easier to implement than Binary Search.

⚙️ How It Works

Choose a block size m = √n, where n is the array size.

Jump through the array in increments of m until the value at the current block end is greater than or equal to the key.

Once the correct block is found, perform a Linear Search within that block.

If the element is found, return its index; otherwise, return -1.

🧩 Algorithm Steps

Calculate step = √n.

Compare the last element of the current block with the key.

If the key is greater, move to the next block:
prev = step; step += √n.

Once you find a block where arr[step] >= key, perform a linear search from prev to step.

If found, return the index; otherwise, return -1.