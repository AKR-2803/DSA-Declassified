# POTD 12-20-2024

## 2415. Reverse Odd Levels of Binary Tree [[Problem](https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/ReverseOddLevelsBinaryTree.java)]

 <!-- ![Easy](https://img.shields.io/badge/Easy-green?style=for-the-badge)  -->
![Medium](https://img.shields.io/badge/Medium-yellow?style=for-the-badge)  
<!-- ![Hard](https://img.shields.io/badge/Hard-red?style=for-the-badge) -->

#### **Tags:** [`Tree`](https://leetcode.com/problem-list/tree/) [`Depth-First Search`](https://leetcode.com/problem-list/depth-first-search/) [`Breadth-First Search`](https://leetcode.com/problem-list/breadth-first-search/) [`Binary Tree`](https://leetcode.com/problem-list/binary-tree/)


## Intuition

- We only need to **reverse the values** at odd levels while maintaining the tree structure.

## Approach

### 1. BFS (Breadth-First Search)

- Use BFS to process the tree level by level.
- Track the level number (starting from 0 for the root).
- If the current level is odd(`level % 2 == 1`), reverse the values of nodes at that level.
    - To reverse the node values we can use two-pointer technique.
    - Keep `start` and `end` pointers at extremes of the list.
    - Keep swapping the values `while(start < end)`!
    - Refer this:

| Two pointer technique to reverse a list                               | 
|--------------------------------------------------------------------------------------| 
| <img src="../images/12-20-2024-reverse-odd-levels-binary-tree-01.jpg" width=500 alt="reverse-odd-levels-binary-tree"/> |
    
- Code for this part:

```java
public void reverseValues(List<TreeNode> currentLevel){
    int start = 0, end = currentLevel.size() - 1;

    while (start < end) {
        // swap values
        int tmp = currentLevel.get(left).val;
        currentLevel.get(start).val = currentLevel.get(end).val;
        currentLevel.get(end).val = tmp;

        // increment start, decrement end
        start++;
        end--;
    }
}
```

- Use a `queue` to traverse the tree level-by-level.
- Store nodes at each level in a temporary list.
- If the level is odd, reverse the values in the list (two-pointer technique).
- Continue traversing until all levels are explored.

### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/ReverseOddLevelsBinaryTree.java)
```java
public TreeNode reverseBFS(TreeNode root) {
    if (root == null) {
        return root;
    }

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    int lvl = 0;

    while (!q.isEmpty()) {
        // no. of elements in current level
        int lvlSize = q.size();

        // storing elements of the current level in a list
        List<TreeNode> currentLevel = new ArrayList<>();

        for (int i = 0; i < lvlSize; i++) {
            TreeNode current = q.poll();
            currentLevel.add(current);

            // add next level nodes in the queue
            if (current.left != null) q.add(current.left);
            if (current.right != null) q.add(current.right);
        }

        // Reverse values at odd levels
        if (lvl % 2 == 1) {
            reverseValues(currentLevel);
        }

        lvl++;
    }
    return root;
}

// remember, we only reverse the values not the nodes itself, hence the tree structure is maintained
public void reverseValues(List<TreeNode> currentLevel){
    int start = 0, end = currentLevel.size() - 1;

    while (start < end) {
        int tmp = currentLevel.get(left).val;
        currentLevel.get(start).val = currentLevel.get(end).val;
        currentLevel.get(end).val = tmp;
        start++;
        end--;
    }
}
```

### 2. DFS (Depth-First Search)
  
- For any two symmetric nodes at the same level, the left node and the right node can have their values swapped.

- Start with the root's left and right children.
- At each recursive call:  
   - If the current level is odd, **swap the values** of the two symmetric nodes.  
- Recursively process:  
   - Left child's **left** subtree with the right child's **right** subtree.  
   - Left child's **right** subtree with the right child's **left** subtree.
   - **WHY?**

| Example Input                              |    Approach                              |
|---------------------------| --------------------------- 
| <img src="../images/12-20-2024-reverse-odd-levels-binary-tree-02.jpg" width=500 alt="reverse-odd-levels-binary-tree"/> | <img src="../images/12-20-2024-reverse-odd-levels-binary-tree-03.jpg" width=500 alt="reverse-odd-levels-binary-tree"/> | 

| DFS Recursive Tree                                              | 
|--------------------------------------------------------------------------------------| 
| <img src="../images/12-20-2024-reverse-odd-levels-binary-tree-04.jpg" width=500 alt="max-chunks-to-make-sorted"/> |


- Stop the recursion when either of the `leftChild` or `rightChild` is `null`.

- **Note**: We only reverse **nodes values** at odd levels which keeps the tree structure maintained.

### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/ReverseOddLevelsBinaryTree.java)
```java
public void reverseDFS(TreeNode leftChild, TreeNode rightChild, int lvl) {
    if (leftChild == null || rightChild == null) {
        return; // Base case: stop when no more nodes to process
    }

    if (lvl % 2 == 1) { // If the current level is odd, swap the values
        int tmp = leftChild.val;
        leftChild.val = rightChild.val;
        rightChild.val = tmp;
    }

    // Recurse for next level
    reverseDFS(leftChild.left, rightChild.right, lvl + 1); // Outer pair
    reverseDFS(leftChild.right, rightChild.left, lvl + 1); // Inner pair
}
```