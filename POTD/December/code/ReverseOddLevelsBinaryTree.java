class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {
        // bfs
        // return reverseBFS(root);

        // dfs
        reverseDFS(root.left, root.right, 1);
        return root;
    }

    // BFS
    public TreeNode reverseBFS(TreeNode root){
        if(root == null){
            return root;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int lvl = 0;

        while(!q.isEmpty()){
            int lvlSize = q.size();
            List<TreeNode> currentLevel = new ArrayList<>();

            for(int i = 0; i < lvlSize; i++){
                TreeNode current = q.poll();
                currentLevel.add(current);

                if(current.left != null){
                    q.add(current.left);
                }

                if(current.right != null){
                    q.add(current.right);
                }
            }

            // if this is odd level, reverse the node values
            if(lvl % 2 == 1){
                // Collections.reverse(currentLevel);
                int left = 0, right = currentLevel.size() - 1;

                // just reverse the node values in the odd level, the structure will be same
                // for example, take Example 1, where `3` and `5` are swapped
                // i.e. `node 3` will have value `5`(which does not change the structure of node below it)
                // similarly `node 5` will now have value `3`
                while(left < right){
                    int tmp = currentLevel.get(left).val;
                    currentLevel.get(left).val = currentLevel.get(right).val;
                    currentLevel.get(right).val = tmp;
                    left++;
                    right--;
                }        
            }
            lvl++;   
        }
        return root;
    }

     // DFS
     public void reverseDFS(TreeNode leftChild, TreeNode rightChild, int lvl){
        if(leftChild == null || rightChild == null){
            return;
        }

        if(lvl % 2 == 1){
            int tmp = leftChild.val;
            leftChild.val = rightChild.val;
            rightChild.val = tmp;
        }

        reverseDFS(leftChild.left, rightChild.right, lvl + 1);
        reverseDFS(leftChild.right, rightChild.left, lvl + 1);
    }
}