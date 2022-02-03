public class Solution {
    public int solve(int n, ArrayList<Integer> arr) {
        long sum = arr.get(0);
        long[][] prefix = new long[n][2];
        prefix[0][0] = sum;
        for (int i = 1; i < n; i++) {
            sum += (long)arr.get(i);
            prefix[i][0] = sum;
        }
        if (sum % 3 != 0) {
            return 0;
        }
        sum /= 3;
        if (prefix[0][0] == sum) {
            prefix[0][1] = 1;
        }
        for (int i = 1; i < n; i++) {
            if (prefix[i][0] == sum) {
                prefix[i][1] = 1;
            }
            prefix[i][1] += prefix[i - 1][1];
        }
        int sum2 = 0, ans = 0;
        for (int i = n - 1; i >= 2; i--) {
            sum2 += (long)arr.get(i);
            if (sum2 == sum) {
                ans += prefix[i - 2][1];
            }
        }
        return ans;
    }
}
