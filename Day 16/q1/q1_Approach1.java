// Solution 1
import java.util.*;

public class Exercise {
    public int findPeak(int[] arr) {
        int n = arr.length;
        int max = arr[0], index = 0;
        for (int i = 1; i < n; i++) {
            if (max < arr[i]) {
                max = arr[i];
                index = i;
            }
        }
        return index;
    }
}
