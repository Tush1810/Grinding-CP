class Solution {
    int n1, n2, n3;
    int[][] dp;
    char arr1[], arr2[], arr3[];
    public boolean isInterleave(String s1, String s2, String s3) {
        arr1 = s1.toCharArray();
        arr2 = s2.toCharArray();
        arr3 = s3.toCharArray();
        n1 = s1.length();
        n2 = s2.length();
        n3 = s3.length();
        if ((n1 + n2) != n3) return false;
        dp = new int[n1 + 1][n2 + 1];
        for (int i = 0; i <= n1; i++) {
            Arrays.fill(dp[i], -1);
        }
        return helper(0, 0);
    }

    private boolean helper(int pos1, int pos2) {
        if (pos1 == n1 && pos2 == n2) {
            return true;
        }
        if (dp[pos1][pos2] != -1) return dp[pos1][pos2] == 1 ? true : false;
        boolean temp = false;
        if (pos1 < n1 && arr1[pos1] == arr3[pos1 + pos2]) {
            temp = helper(pos1 + 1, pos2);
        }
        if (pos2 < n2 && !temp && arr2[pos2] == arr3[pos1 + pos2]) {
            temp = helper(pos1, pos2 + 1);
        }
        if (temp) dp[pos1][pos2] = 1;
        else dp[pos1][pos2] = 0;
        return temp;
    }
}
