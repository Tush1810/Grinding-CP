public class Solution {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    private class Num {
        int num, index;
        Num(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }
    public int maximumGap(final List<Integer> a) {
        Num[] arr = new Num[a.size()];
        for (int i = 0; i < a.size(); i++) {
            arr[i] = new Num(a.get(i), i);
        }
        Arrays.sort(arr, (o1, o2)->o1.num - o2.num);
        int minIndex = arr[0].index, ans = 0;
        for (int i = 1; i < arr.length; i++) {
            ans = Math.max(ans, arr[i].index - minIndex);
            minIndex = Math.min(minIndex, arr[i].index);
        }
        return ans;
    }
}
