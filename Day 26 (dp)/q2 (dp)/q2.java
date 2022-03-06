class Solution {
    private int n;
    private long mod = 1_000_000_007, dp[][];
    public int countOrders(int nn) {
        n = nn;
        dp = new long[n + 1][n + 1];
        for (long[] i : dp) {
            Arrays.fill(i, -1l);
        }
        return (int)helper(0, 0);
    }
    private long helper(int countP, int countD) {
        if (n == countP && n == countD) {
            return 1l;
        }
        if (dp[countP][countD] != -1) {
            return dp[countP][countD];
        }
        if (countP < n) {
            dp[countP][countD] = (helper(countP + 1, countD) * (n - countP)) % mod;
        } else dp[countP][countD] = 0;
        if (countD < countP && countD < n) {
            dp[countP][countD] = (dp[countP][countD] + (helper(countP, countD + 1) * (countP - countD)) % mod) % mod;
        }
        return dp[countP][countD];
    }
}
