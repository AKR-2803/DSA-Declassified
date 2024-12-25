class Solution {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> {
            if(a[0] != b[0]){
                return a[0] - b[0]; // when differnt values, go for lesser one
            } else{
                return a[1] - b[1]; // when same value, go for lesser index (value appearing first)
            }   
        });

        for(int i = 0; i < nums.length; i++){
            heap.offer(new int[]{ nums[i], i});
        }

        // take min element each time for `k` times
        for(int i = 0; i < k; i++){
            int[] pairMin = heap.poll();
            int idx = pairMin[1];

            nums[idx] *= multiplier;
            
            heap.offer(new int[]{nums[idx], idx});
        }
        return nums;
    }
}