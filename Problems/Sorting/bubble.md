## Sorting 0s, 1s and 2s | Dutch National Flag Problem/Algorithm\

##### [Leetcode](https://leetcode.com/problems/sort-colors/description/) | [GeeksForGeeks](https://www.geeksforgeeks.org/problems/sort-an-array-of-0s-1s-and-2s4231/1)

##### Brute Force: Bubble sort

#### Optimized: We have 3 things/colors represented by 0, 1, and 2

As we need to sort them accordingly(order of 0->1->2). 

0s -> start, 1s -> middle, 2s -> end

We may use 2 pointers `start` and `end`. And a pointer `i`.

`start = 0` (first index of the array)
`end = arr.length - 1` (last index of the array)



0's will be swapped to the `start` and 2s will be swapped with `end`

