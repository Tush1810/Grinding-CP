class Solution {
    public int longestConsecutive(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
        }
        int ans = 1;
        for (int i = 0; i < n; i++) {
            if (!set.contains(arr[i] + 1)) {
                int temp = arr[i] - 1;
                int count = 1;
                while (set.contains(temp)) {
                    temp--;
                    count++;
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }
}
