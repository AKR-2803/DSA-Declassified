import java.util.Map;
import java.util.Arrays;

public class BooleanParenthesization {
    public static void main(String[] args) {
        int n = 7;
        String s = "T|T&F^T";

        System.out.println(countWays(n, s));
    }

    static int countWays(int n, String s) {
        int i = 0;
        int j = n - 1;
        int isTrue = 1;

        // recursion
        // we need the expression to calculate ways for "TRUE"
        // return solve(s.toCharArray(), i, j, isTrue);

        // memoization (HashMap<>) TLE!
        // return solveMemo(s.toCharArray(), i, j, isTrue, new HashMap<>());

        // memoization (dp[][][])
        // [i][j][2], 2 is for true and false.
        // imagine 2 blocks of 2D arrays (boolean_parenthesization_4.png)
        int[][][] dp = new int[n + 1][n + 1][2];

        for (int[][] row1 : dp) {
            for (int[] row2 : row1) {
                Arrays.fill(row2, -1);
            }
        }

        return solveMemoDP(s.toCharArray(), i, j, isTrue, dp);
    }

    // memoization using dp[][][] array
    private static int solveMemoDP(char[] s, int i, int j, int isTrue, int[][][] dp) {
        if (i > j) {
            return 0;
        }

        if (i == j) {
            if (isTrue == 1) {
                return s[i] == 'T' ? 1 : 0;
            } else {
                return s[i] == 'F' ? 1 : 0;
            }
        }

        if (dp[i][j][isTrue] != -1) {
            return dp[i][j][isTrue];
        }

        int ways = 0;

        for (int k = i + 1; k <= j - 1; k = k + 2) {
            int LT = solveMemoDP(s, i, k - 1, 1, dp);
            int LF = solveMemoDP(s, i, k - 1, 0, dp);
            int RT = solveMemoDP(s, k + 1, j, 1, dp);
            int RF = solveMemoDP(s, k + 1, j, 0, dp);

            switch (s[k]) {
                case '&':
                    if (isTrue == 1) {
                        ways += LT * RT;
                    } else {
                        ways += LT * RF + LF * RT + LF * RF;
                    }
                    break;

                case '|':
                    if (isTrue == 1) {
                        ways += LT * RF + LF * RT + LT * RT;
                    } else {
                        ways += LF * RF;
                    }
                    break;

                case '^':
                    if (isTrue == 1) {
                        ways += LT * RF + LF * RT;
                    } else {
                        ways += LT * RT + LF * RF;
                    }
                    break;
            }

            // modulo 1003 given in question
            ways %= 1003;
        }
        return dp[i][j][isTrue] = ways;
    }

    // ***********************************************************************

    // Memoization (TLE)
    private static int solveMemo(char[] s, int i, int j, int isTrue, Map<String, Integer> mp) {
        if (i > j) {
            return 0;
        }

        if (i == j) {
            if (isTrue == 1) {
                return s[i] == 'T' ? 1 : 0;
            } else {
                return s[i] == 'F' ? 1 : 0;
            }
        }

        String mapKey = i + "-" + j + "-" + isTrue;
        int res = mp.getOrDefault(mapKey, -1);

        if (res != -1) {
            return res;
        }

        // if(mp.containsKey(mapKey)) {
        // return mp.get(mapKey);
        // }

        long ways = 0;

        for (int k = i + 1; k <= j - 1; k = k + 2) {
            int LT = solveMemo(s, i, k - 1, 1, mp);
            int LF = solveMemo(s, i, k - 1, 0, mp);
            int RT = solveMemo(s, k + 1, j, 1, mp);
            int RF = solveMemo(s, k + 1, j, 0, mp);

            if (s[k] == '&') {
                if (isTrue == 1) {
                    ways += LT * RT;
                } else {
                    ways += LT * RF + LF * RT + LF * RF;
                }
            }

            if (s[k] == '|') {
                if (isTrue == 1) {
                    ways += LT * RF + LF * RT + LT * RT;
                } else {
                    ways += LF * RF;
                }
            }

            if (s[k] == '^') {
                if (isTrue == 1) {
                    ways += LT * RF + LF * RT;
                } else {
                    ways += LT * RT + LF * RF;
                }
            }

            ways %= 1003;
        }

        int ans = (int) ways;

        mp.put(mapKey, ans);
        return ans;
    }

    // ***********************************************************************

    // recursion

    static int solve(char[] s, int i, int j, int isTrue) {
        if (i > j)

        {
            return 0;
        }

        if (i == j) {
            if (isTrue == 1) {
                return s[i] == 'T' ? 1 : 0;
            } else {
                return s[j] == 'F' ? 1 : 0;
            }
        }

        long ans = 0;

        // IMP: `k = k + 2` (`k` points to operators)
        for (int k = i + 1; k <= j - 1; k = k + 2) {
            int LT = solve(s, i, k - 1, 1);
            int LF = solve(s, i, k - 1, 0);
            int RT = solve(s, k + 1, j, 1);
            int RF = solve(s, k + 1, j, 0);

            if (s[k] == '&') {
                if (isTrue == 1) {
                    ans += (LT * RT);
                } else {
                    ans += (LF * RT) + (LT * RF) + (LF * RF);
                }
            }

            if (s[k] == '|') {
                if (isTrue == 1) {
                    ans += (LT * RF) + (LF * RT) + (LT * RT);
                } else {
                    ans += (LF * RF);
                }
            }

            if (s[k] == '^') {
                if (isTrue == 1) {
                    ans += (LT * RF) + (LF * RT);
                } else {
                    ans += (LT * RT) + (LF * RF);
                }
            }
        }

        int ways = (int) ans;

        return ways % 1003;
    }

}