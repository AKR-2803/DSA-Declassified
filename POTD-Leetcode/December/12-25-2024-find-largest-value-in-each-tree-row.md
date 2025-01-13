# POTD 12-25-2024

## 515. Find Largest Value in Each Tree Row [[Problem](https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/FindLargestValueInEachTreeRow.java)]

<!-- ![Easy](https://img.shields.io/badge/Easy-green?style=for-the-badge)  -->
![Medium](https://img.shields.io/badge/Medium-yellow?style=for-the-badge)  
<!-- ![Hard](https://img.shields.io/badge/Hard-red?style=for-the-badge) -->

#### **Tags:** [`Tree`](https://leetcode.com/problem-list/tree/) [`Depth-First Search`](https://leetcode.com/problem-list/depth-first-search/) [`Breadth-First Search`](https://leetcode.com/problem-list/breadth-first-search/) [`Binary Tree`](https://leetcode.com/problem-list/binary-tree/)

## Intuition
- Traversing level-by-level(row) = BFS

## Approach
- Traverse using BFS:
- Use a queue to perform a BFS of the tree.
- For each level, keep updating maximum element.
- After exploring the level, add max element in the result

### Complexity Analysis
- **Time Complexity: _O(n)_**
    - BFS: Visiting all nodes once `O(n)`.

- **Space Complexity: _O(n)_**
    - Queue has at max `n/2` elements (Complete binary tree has maximum `n/2` elements at last level)

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/FindLargestValueInEachTreeRow.java)
```java
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        
        if(root == null){
            return result;
        }

        bfs(root, result);
        return result;
    }

    public void bfs(TreeNode root, List<Integer> result){
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            int lvlSize = q.size();

            // tracks maximum element at a particular level(row)
            int maxElement = Integer.MIN_VALUE;

            for(int i = 0; i < lvlSize; i++){
                TreeNode current = q.poll();

                if(current.left != null){
                    q.add(current.left);
                }
                if(current.right != null){
                    q.add(current.right);
                }
                
                // keep updating maximum element in the current level(row)
                if(maxElement < current.val){
                    maxElement = current.val;
                }
            }
            
            // max element of this level(row)
            result.add(maxElement);
        }
    }
}
```