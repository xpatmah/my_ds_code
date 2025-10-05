🧭 Interpolation Search
📘 Overview

Interpolation Search is an improved variant of Binary Search that works efficiently on uniformly distributed sorted arrays.
Instead of always checking the middle element, it estimates the position of the target element using a mathematical interpolation formula — similar to how you’d look up a value in a telephone book.

⚙️ How It Works

Unlike Binary Search, which splits the search range in half each time, Interpolation Search uses the position formula to predict where the key might be based on its value:

𝑝
𝑜
𝑠
=
𝑙
𝑜
𝑤
+
(
𝑘
𝑒
𝑦
−
𝑎
𝑟
𝑟
[
𝑙
𝑜
𝑤
]
)
×
(
ℎ
𝑖
𝑔
ℎ
−
𝑙
𝑜
𝑤
)
(
𝑎
𝑟
𝑟
[
ℎ
𝑖
𝑔
ℎ
]
−
𝑎
𝑟
𝑟
[
𝑙
𝑜
𝑤
]
)
pos=low+
(arr[high]−arr[low])
(key−arr[low])×(high−low)
​


low and high are the current search boundaries.

arr[low] and arr[high] are the values at those indices.

The formula estimates where the key might be, assuming the values are uniformly distributed.

🧩 Algorithm Steps

Start with the whole array: low = 0, high = n - 1.

While low <= high and key lies between arr[low] and arr[high]:

Estimate the position using the interpolation formula.

If arr[pos] == key, return pos.

If arr[pos] < key, move to the right: low = pos + 1.

If arr[pos] > key, move to the left: high = pos - 1.

If not found, return -1.