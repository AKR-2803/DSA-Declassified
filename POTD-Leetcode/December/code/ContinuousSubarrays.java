class Solution {
    public long continuousSubarrays(int[] nums) {
        int n = nums.length;
        long cnt = 0;
        
        // Two deques to maintain the maximum and minimum values in the current window
        Deque<Integer> maxDeq = new LinkedList<>();
        Deque<Integer> minDeq = new LinkedList<>();

        int start = 0;

        // Iterate through the array with the `end` pointer
        for(int end = 0; end < n; end++){
            // Maintain decreasing order in maxDeq
            while(!maxDeq.isEmpty() && nums[end] > maxDeq.peekLast()){
                maxDeq.pollLast();
            }

            // Maintain increasing order in minDeq
            while(!minDeq.isEmpty() && nums[end] < minDeq.peekLast()){
                minDeq.pollLast();
            }

            // Add current element to both deques
            maxDeq.offerLast(nums[end]);
            minDeq.offerLast(nums[end]);

            // while the continuous array conditions are violated
            // keep shrinking the window from left, i.e. increment `start`
            while(maxDeq.peekFirst() - minDeq.peekFirst() > 2){
                if(nums[start] == maxDeq.peekFirst()){
                    maxDeq.pollFirst();
                }

                if(nums[start] == minDeq.peekFirst()){
                    minDeq.pollFirst();
                }

                start++;
            }

            // for valid subarray, count all possible subarrays
            // these are all subarrays ending at `end` and starting between `start` and `end` are valid
            cnt += end - start + 1;
        }
        return cnt;
    }
}