public class SubsetSum {
    public static void main(String[] args) {
        int[] arr = { 3, 34, 4, 12, 5, 2 };
        int n = arr.length;
        int sum = 9;

        // Recursion
        // System.out.println(isSumRec(n, arr, sum));

        // Tabulation
        // boolean[][] dp = new boolean[N + 1][sum + 1];
        // return isSumTab(N, arr, sum, dp);
    }

    // Recursion
    // TLE! on Test Case 33/83
    static boolean isSumRec(int N, int[] arr, int sum){
        if(sum == 0){
            return true;
        }

        if(N == 0){
            return false;
        }

        if(arr[N - 1] <= sum){
            boolean takeElement = isSumRec(N - 1, arr, sum - arr[N - 1]);
            boolean dontTakeElement = isSumRec(N - 1, arr, sum);

            return takeElement || dontTakeElement;
        }
        return isSumRec(N - 1, arr, sum);
    }

    // Memoization Problem : [How will you know that the answer was computed before?]
    /*
        In Integer array, you were able to tell after checking for `dp[i][j] != -1`, but
        this dp array is boolean, and if you do `dp[i][j] != false`, you can't make sure if
        its the `false` which came default when you initialized the dp array or it is the `false`
        that was computed at a particular step in the recursion
        i.e., it will not be possible to ensure whether the false is form default value of dp
        array or the from a previously calculated subproblem!

        One thing I thought is to keep another integer 2D array to check if the cell was changed
        via a subproblem or not, but anyway tabulation gives same complexity and will be more preferable
        Hence we directly use Tabulation
    */
    /*
        static boolean isSumMemo(int N, int[] arr, int sum, boolean[][] dp){
            if(sum == 0){
                return true;
            }

            if(N == 0){
                return false;
            }

            // It will not be possible to ensure whether the `false` is form default value of
            // dp array or the from a previously calculated subproblem!
            // Hence we directly use Tabulation instead of memoization
            if(dp[N][sum] != false){
                return dp[N][sum];
            }

            if(arr[N - 1] <= sum){

                boolean takeElement = isSumMemo(N - 1, arr, sum - arr[N - 1], dp);
                boolean dontTakeElement = isSumMemo(N - 1, arr, sum, dp);

                return dp[N][sum] = (takeElement || dontTakeElement);
            }

            return dp[N][sum] = isSumMemo(N - 1, arr, sum, dp);
        }
    */

    // Tabulation
    static boolean isSumTab(int N, int[] arr, int sum, boolean[][] dp){
        for(int i = 0; i <= N; i++){
            for(int j = 0; j <= sum; j++){
                if(i == 0){
                    dp[i][j] = false;
                }
                if(j == 0){
                    dp[i][j] = true;
                }
            }
        }

        // i -> N (number of elements)
        // j -> `sum` required
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= sum; j++){
                if(arr[i - 1] <= j){
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                }
                else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][sum];
    }

}
