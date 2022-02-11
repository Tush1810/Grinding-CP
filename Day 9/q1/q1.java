public class Solution {
    public int solve(ArrayList<String> A) {
        Collections.sort(A);
        double a, b, c;
        for (int i = 0, j = A.size() - 1; i + 1 < j;) {
            a = Double.valueOf(A.get(i));
            b = Double.valueOf(A.get(i + 1));
            c = Double.valueOf(A.get(j));

            if (a + b + c < 2 && a + b + c > 1) {
                return 1;
            } else if (a + b + c >= 2) {
                j--;
            } else {
                i++;
            }
        }
        return 0;
    }
}
