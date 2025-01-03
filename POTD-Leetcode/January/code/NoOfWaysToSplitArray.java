class Solution {
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        long[] prefix = new long[n + 1]; // long to prevent overflow
        
        // compute prefix sum
        for(int i = 0; i < n; i++){
            prefix[i + 1] = prefix[i] + nums[i];
        }

        long totalSum = prefix[n];
        int validSplits = 0;

        for(int i = 0; i < n - 1; i++){
            long sumFirst = prefix[i + 1];
                
            /*
                sumLast = totalSum - sumFirst;
                To check
                sumFirst >= sumLast
                sumFirst >= totalSum - sumFirst
                2*sumFirst >= totalSum
            */
            if(2*sumFirst >= totalSum){
                validSplits += 1;
            }
        }
        return validSplits;
    }
}