class Solution {
    int totalWays = 0;

    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        // recursion
        // findWaysRec(nums, 0, 0, target);
        // return totalWays;

        // memoization
        int totalSum = 0;

        for(int num : nums){
            totalSum += num;
        }
        
        // For sum range [-8,+8] => new range => [0, 16] indices of the `dp` array
        int[][] dp = new int[n][2 * totalSum + 1];
        
        for(int[] row : dp){
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        return findWaysMemo(nums, 0, 0, target, dp, totalSum);  
    }

    // memoization => TC: O(n*totalSum)
    public int findWaysMemo(int[] nums, int n, int currentSum, int target, int[][] dp, int totalSum){
        if(n == nums.length){
            if(currentSum == target){
                return 1; // found 1 way to reach this target
            } else{
                return 0; // did not find a way
            }
        }

        // handling negative indices
        // if we already calculated this subproblem before, just return its value
        if(dp[n][currentSum + totalSum] != Integer.MIN_VALUE){
            return dp[n][currentSum + totalSum];
        }

        // take positive sign
        int positive = findWaysMemo(nums, n + 1, currentSum + nums[n], target, dp, totalSum);

        // take negative sign
        int negative = findWaysMemo(nums, n + 1, currentSum - nums[n], target, dp, totalSum);

        // store total ways from both choices
        dp[n][currentSum + totalSum] = positive + negative;

        return dp[n][currentSum + totalSum];
    }

    // recursion
    // TC: O(2^n) very bad
    public void findWaysRec(int[] nums, int currentIdx, int currentSum, int target){
        if(currentIdx == nums.length){
            if(currentSum == target){
                totalWays += 1;
            }
        }

        else{
            // take negative 
            findWaysRec(nums, currentIdx + 1, currentSum - nums[currentIdx], target);

            // take positive
            findWaysRec(nums, currentIdx + 1, currentSum + nums[currentIdx], target);
        }
    }
}