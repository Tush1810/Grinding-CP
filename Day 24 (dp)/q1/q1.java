class Solution {
    private int n, dp[][], k;
    private ArrayList<Integer> list, ans;
    boolean found;
    public List<Integer> largestDivisibleSubset(int[] arr) {
        n = arr.length;
        Arrays.sort(arr);
        dp = new int[n][n + 2];
        k = 1001;
        list = new ArrayList<Integer>();
        for (int i[] : dp) Arrays.fill(i, -1);
        k = helper(arr, 0, n + 1);
        ans = new ArrayList<Integer>();
        int lastAdded = n + 1, last = 0;
        for (int i = 1; i < n; i++) {
            if (dp[i][lastAdded] == k) {
                last = i;
            } else {
                k--;
                lastAdded = last;
                ans.add(arr[last]);
                last = i;
            }
        }
        if (k == 0) return ans;
        if (ans.size() == 0 || (arr[last] % ans.get(ans.size() - 1) == 0)) {
            ans.add(arr[last]);
        } else ans.add(arr[lastAdded]);

        return ans;
    }

    private int helper(int[] arr, int i, int lastAdded) {
        if (i == n) {
            return 0;
        }
        if (check(lastAdded) && dp[i][lastAdded] != -1) {
            return dp[i][lastAdded];
        }
        if (!check(lastAdded) || (check(lastAdded) && ((arr[i] % arr[lastAdded]) == 0))) {
            dp[i][lastAdded] = 1 + helper(arr, i + 1, i);
        }
        int temp = helper(arr, i + 1, lastAdded);
        if (temp > dp[i][lastAdded]) {
            dp[i][lastAdded] = temp;
        }
        return dp[i][lastAdded];
    }
    private boolean check(int i) {
        return (i != (n + 1));
    }
}
