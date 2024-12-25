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