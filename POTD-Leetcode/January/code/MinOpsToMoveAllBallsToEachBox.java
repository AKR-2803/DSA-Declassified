class Solution {
    public int[] minOperations(String boxes) {
        char[] box = boxes.toCharArray();
        int N = box.length; 
        int[] res = new int[N]; // array to store the results

        for (int i = 0; i < N; i++) {
            int ops = 0; // total operations for box `i`

            // Check the distance of all other boxes from the current box
            for (int j = 0; j < N; j++) {
                if (box[j] == '1') {  // If box `j` contains a ball
                    ops += Math.abs(i - j); // Add the distance to the total operations
                }
            }
            res[i] = ops;
        }
        return res;
    }
}