class Solution {
    Pair[] arr;
    private int[][] dp;
    private class Pair {
        int first, second;
        Pair(int a, int b) {
            this.first = a;
            this.second = b;
        }
    }
    public int deleteAndEarn(int[] nums) {
        HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();
        for (int i : nums) {
            memo.put(i, memo.getOrDefault(i, 0) + 1);
        }
        arr = new Pair[memo.size()];
        int count = 0;
        for (int i : memo.keySet()) {
            arr[count++] = new Pair(i, memo.get(i));
        }
        Arrays.sort(arr, (o1, o2)->o1.first - o2.first);
        int[][] dp = new int[arr.length + 1][arr.length + 1];
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            dp[i][i] = arr[i].first * arr[i].second;
        }
        for (int i = 1; i < n; i++) {
            if (arr[i - 1].first + 1 == arr[i].first) {
                dp[i - 1][i] = Math.max(dp[i - 1][i - 1], dp[i][i]);
            } else dp[i - 1][i] = dp[i - 1][i - 1] + dp[i][i];
        }
        for (int length = 2; length < arr.length; length++) {
            for (int i = 0; i + length < n; i++) {
                if (arr[i + length - 1].first + 1 == arr[i + length].first) {
                    dp[i][i + length] = Math.max(dp[i][i + length - 2] + arr[i + length].first * arr[i + length].second, dp[i][i + length - 1]);
                } else {
                    dp[i][i + length] = dp[i][i + length - 1] + arr[i + length].first * arr[i + length].second;
                }
            }
        }
        return dp[0][n - 1];
    }
}
