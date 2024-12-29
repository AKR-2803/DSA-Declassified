class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;

        // Sums for the three sliding windows
        int sum1 = 0, sum2 = 0, sum3 = 0;

        // Maximum sums for individual and combined subarrays
        int max1 = 0, max12 = 0, max123 = 0;

        // Indices to store the starting positions of subarrays
        int index1 = 0, index12_1 = 0, index12_2 = k;
        int[] result = {0, k, 2 * k}; // Default result with initial indices

        // Calculate initial sums for the first three subarrays
        for (int i = 0; i < k; i++) {
            sum1 += nums[i];
            sum2 += nums[i + k];
            sum3 += nums[i + 2 * k];
        }

        // Initialize maximum sums
        max1 = sum1;
        max12 = sum1 + sum2;
        max123 = sum1 + sum2 + sum3;

        // Iterate through the array for all possible starting points
        for (int i = 0; i <= n - 3 * k; i++) {
            // Update sums for sliding windows if not the first iteration
            if (i > 0) {
                sum1 = sum1 - nums[i - 1] + nums[i + k - 1];
                sum2 = sum2 - nums[i + k - 1] + nums[i + 2 * k - 1];
                sum3 = sum3 - nums[i + 2 * k - 1] + nums[i + 3 * k - 1];
            }

            // Update max1 and its index
            if (sum1 > max1) {
                max1 = sum1;
                index1 = i;
            }

            // Update max12 and its indices
            if (max1 + sum2 > max12) {
                max12 = max1 + sum2;
                index12_1 = index1;
                index12_2 = i + k;
            }

            // Update max123 and result indices
            if (max12 + sum3 > max123) {
                max123 = max12 + sum3;
                result = new int[]{index12_1, index12_2, i + 2 * k};
            }
        }

        // Return the indices of the three subarrays
        return result;
    }
}