public class Solution {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public ArrayList<Integer> findPerm(final String s, int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (s.charAt(s.length() - 1) == 'D') ans.add(1);
        else ans.add(n);
        int start = 1, end = n;
        if (ans.get(0) == 1) start++;
        else end--;

        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) == 'D') {
                ans.add(start);
                start++;
            } else {
                ans.add(end);
                end--;
            }
        }
        ans.add(start);
        for (int i = 0; i < n - 1 - i; i++) {
            ans.set(i, ans.get(i) + ans.get(n - 1 - i));
            ans.set(n - 1 - i, ans.get(i) - ans.get(n - 1 - i));
            ans.set(i, ans.get(i) - ans.get(n - 1 - i));
        }
        return ans;
    }
}
