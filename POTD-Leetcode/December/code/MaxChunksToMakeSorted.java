class Solution {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int maxSoFar = 0;
        int cnt = 0;

        for(int i = 0; i < n; i++){
            maxSoFar = Math.max(maxSoFar, arr[i]);

            if(maxSoFar == i){
                cnt += 1;
            }
        }

        return cnt;
    }
}