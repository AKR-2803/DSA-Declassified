class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        return findMaxScore(values);
    }

    public int findMaxScore(int[] values){
        int n = values.length;
        int maxi = 0;
        int maxScore = 0;

        for(int i = 0; i < n; i++){
            // check for 2nd spot(jth spot)
            if(i > 0){
                int j = i; // using variable `j` for better understanding

                // keep updating maximum score
                maxScore = Math.max(maxScore, maxi + values[j] - j);
            }

            // update the ith spot when a higher value is found
            maxi = Math.max(maxi, values[i] + i);
        }

        return maxScore;
    }
}