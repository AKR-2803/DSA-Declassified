class Solution {
    public int minimumOperations(TreeNode root) {
        int totalSwaps = 0;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        // BFS
        while(!q.isEmpty()){
            int lvlSize = q.size(); // total nodes at current level

            int[] currentLevel = new int[lvlSize]; // array with node values at this level

            for(int i = 0; i < lvlSize; i++){
                TreeNode currentNode = q.poll();   // remove the current node

                currentLevel[i] = currentNode.val; // store current node's value

                if(currentNode.left != null){
                    q.add(currentNode.left);  // if present, add left child 
                }

                if(currentNode.right != null){
                    q.add(currentNode.right); // if present, add right child
                }
            }
            totalSwaps += findSwaps(currentLevel); // count swaps to sort this level
        }
        return totalSwaps; // total swaps required
    }

    // find total swaps needed to sort a particular level
    public int findSwaps(int[] original){
        int[] sortedArr = original.clone();   // sort copy of array, to know the correct index positions
        Arrays.sort(sortedArr);

        int swaps = 0;

        Map<Integer, Integer> mp = new HashMap<>(); // map to track element indices in the original array

        // store [elem : index] as key value pair in the Map
        for(int i = 0; i < original.length; i++){
            mp.put(original[i], i);
        }

        // count swaps to sort the array
        for(int i = 0; i < original.length; i++){
            if(original[i] != sortedArr[i]){    // if the current element is not in the correct position

                int pos = mp.get(sortedArr[i]); // get the correct position of the this element
                mp.put(original[i], pos);       // update the map with the swapped element's new position
                original[pos] = original[i];    // swap the elements in the original array

                swaps += 1;
            }
        }
        return swaps;
    }
}