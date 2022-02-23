import java.util.*;
import java.lang.*;
import java.math.BigInteger;

import java.io.*;

public class q5 implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new q5(), "whatever", 1 << 26).start();
    }
    //

    private FastScanner sc;
    private PrintWriter pw;

    public void run() {
        try {
            boolean isSumitting = true;
            // isSumitting = false;
            if (isSumitting) {
                pw = new PrintWriter(System.out);
                sc = new FastScanner(new BufferedReader(new InputStreamReader(System.in)));
            } else {
                pw = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
                sc = new FastScanner(new BufferedReader(new FileReader("input.txt")));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

        int t = sc.nextInt();
        // int t = 1;
        while (t-- > 0) {
            // sc.nextLine();
            // System.out.println("for t=" + t);
            solve();
        }
        pw.close();
    }

    public long mod = 1_000_000_007;
    private class Pair {
        int first, second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    ArrayList<Pair> ans;
    ArrayList<Integer> ans2;

    public void solve() {
        int n = sc.nextInt();
        int arr[] = new int[n];
        HashMap<Integer, Integer> memo = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            memo.put(arr[i], memo.getOrDefault(arr[i], 0) + 1);
        }
        for (int i : memo.values()) {
            if (i % 2 == 1) {
                pw.println(-1);
                return;
            }
        }
        ans = new ArrayList<Pair>();
        ans2 = new ArrayList<Integer>();
        ArrayList<Integer> missingInSecondHalf = new ArrayList<Integer>();
        ArrayList<Integer> missingInFirstHalf = new ArrayList<Integer>();

        memo = new HashMap<Integer, Integer>();

        for (int i = 0; i < n / 2; i++) {
            memo.put(arr[i], memo.getOrDefault(arr[i], 0) + 1);
        }
        for (int i = n / 2; i < n; i++) {
            if (!memo.containsKey(arr[i])) {
                missingInFirstHalf.add(arr[i]);
                memo.put(arr[i], 1);
            } else {
                memo.replace(arr[i], memo.get(arr[i]) - 1);
                if (memo.get(arr[i]) == 0) {
                    memo.remove(arr[i]);
                }
            }
        }
        for (int key : memo.keySet()) {
            int temp = memo.get(key) / 2;
            for (int i = 0; i < temp; i++) {
                missingInSecondHalf.add(key);
            }
        }
        int[] arr2 = new int[n + missingInFirstHalf.size() * 2 + missingInSecondHalf.size() * 2];
        int index = 0;
        int prev = 0;
        for (int i : missingInFirstHalf) {
            arr2[index] = i;
            arr2[index + 1] = i;
            index += 2;
            ans.add(new Pair(prev, i));
            prev += 2;
        }
        for (int i = 0; i < n; i++) {
            arr2[index++] = arr[i];
        }
        int prev2 = 0;
        for (int i : missingInSecondHalf) {
            arr2[index] = i;
            arr2[index + 1] = i;
            index += 2;
            ans.add(new Pair(prev + n + prev2, i));
            prev2 += 2;
        }
        prev = 0;
        n = arr2.length;

        for (int i = 0; i < n / 2; i++) {
            // pw.println("i=" + i);
            int find = arr2[n - i - 1];
            if (arr2[(n / 2) - i - 1] == find) continue;
            boolean found = false;
            for (int j = 0; j < n / 2; j++) {
                if (arr2[j] == find) {
                    if (j == 0) break;
                    // pw.println("Reversing at j=" + j);
                    prev = reverse(arr2, 0, j, prev);
                    break;
                }
            }
            prev = reverse(arr2, 0, n / 2 - i - 1, prev);
            // for (int q = 0; q < n; q++) {
            //     pw.print(arr2[q] + " ");
            // }
            // pw.println();
            // pw.println("i=" + i);
        }

        pw.println(ans.size());
        for (Pair p : ans) {
            pw.println(p.first + " " + p.second);
        }
        pw.println(ans2.size() + 1);
        for (int i : ans2) {
            pw.print(i + " ");
        }
        pw.println(n);
    }

    private int reverse(int[] arr, int start, int end, int prev) {
        // pw.println("Start=" + start + " , end=" + end);
        int count = 0, target = end - start + 1;
        ans2.add(target * 2);
        while (count != target) {
            count++;
            Pair p = new Pair(prev + target + count - 1, arr[start + count - 1]);
            ans.add(p);
        }
        count = start;
        target = end;
        int temp;
        while (count < target) {
            temp = arr[target];
            arr[target] = arr[count];
            arr[count] = temp;
            count++;
            target--;
        }
        return prev + (end - start + 1) * 2;
    }



    class FastScanner {
        private BufferedReader reader = null;
        private StringTokenizer tokenizer = null;

        public FastScanner(BufferedReader bf) {
            reader = bf;
            tokenizer = null;
        }

        public String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken("\n");
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public float nextFloat() {
            return Float.parseFloat(next());
        }

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        public String[] nextStringArray(int n) {
            String[] a = new String[n];
            for (int i = 0; i < n; i++) {
                a[i] = next();
            }
            return a;
        }

        public long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextLong();
            }
            return a;
        }
    }



    private static class Sorter {
        public static <T extends Comparable<? super T>> void sort(T[] arr) {
            Arrays.sort(arr);
        }
        public static <T> void sort(T[] arr, Comparator<T> c) {
            Arrays.sort(arr, c);
        }
        public static <T> void sort(T[][] arr, Comparator<T[]> c) {
            Arrays.sort(arr, c);
        }
        public static <T extends Comparable<? super T>> void sort(ArrayList<T> arr) {
            Collections.sort(arr);
        }
        public static <T> void sort(ArrayList<T> arr, Comparator<T> c) {
            Collections.sort(arr, c);
        }
        public static void normalSort(int[] arr) {
            Arrays.sort(arr);
        }
        public static void normalSort(long[] arr) {
            Arrays.sort(arr);
        }
        public static void sort(int[] arr) {
            timSort(arr);
        }
        public static void sort(int[] arr, Comparator<Integer> c) {
            timSort(arr, c);
        }
        public static void sort(int[][] arr, Comparator<Integer[]> c) {
            timSort(arr, c);
        }
        public static void sort(long[] arr) {
            timSort(arr);
        }
        public static void sort(long[] arr, Comparator<Long> c) {
            timSort(arr, c);
        }
        public static void sort(long[][] arr, Comparator<Long[]> c) {
            timSort(arr, c);
        }
        private static void timSort(int[] arr) {
            Integer[] temp = new Integer[arr.length];
            for (int i = 0; i < arr.length; i++) temp[i] = arr[i];
            Arrays.sort(temp);
            for (int i = 0; i < arr.length; i++) arr[i] = temp[i];
        }
        private static void timSort(int[] arr, Comparator<Integer> c) {
            Integer[] temp = new Integer[arr.length];
            for (int i = 0; i < arr.length; i++) temp[i] = arr[i];
            Arrays.sort(temp, c);
            for (int i = 0; i < arr.length; i++) arr[i] = temp[i];
        }
        private static void timSort(int[][] arr, Comparator<Integer[]> c) {
            Integer[][] temp = new Integer[arr.length][arr[0].length];
            for (int i = 0; i < arr.length; i++)
                for (int j = 0; j < arr[0].length; j++)
                    temp[i][j] = arr[i][j];
            Arrays.sort(temp, c);
            for (int i = 0; i < arr.length; i++)
                for (int j = 0; j < arr[0].length; j++)
                    temp[i][j] = arr[i][j];
        }
        private static void timSort(long[] arr) {
            Long[] temp = new Long[arr.length];
            for (int i = 0; i < arr.length; i++) temp[i] = arr[i];
            Arrays.sort(temp);
            for (int i = 0; i < arr.length; i++) arr[i] = temp[i];
        }
        private static void timSort(long[] arr, Comparator<Long> c) {
            Long[] temp = new Long[arr.length];
            for (int i = 0; i < arr.length; i++) temp[i] = arr[i];
            Arrays.sort(temp, c);
            for (int i = 0; i < arr.length; i++) arr[i] = temp[i];
        }
        private static void timSort(long[][] arr, Comparator<Long[]> c) {
            Long[][] temp = new Long[arr.length][arr[0].length];
            for (int i = 0; i < arr.length; i++)
                for (int j = 0; j < arr[0].length; j++)
                    temp[i][j] = arr[i][j];
            Arrays.sort(temp, c);
            for (int i = 0; i < arr.length; i++)
                for (int j = 0; j < arr[0].length; j++)
                    temp[i][j] = arr[i][j];
        }
    }


    public long fastPow(long x, long y, long mod) {
        if (y == 0) return 1;
        if (y == 1) return x % mod;

        long temp = fastPow(x, y / 2, mod);
        long ans = (temp * temp) % mod;

        return (y % 2 == 1) ? (ans * (x % mod)) % mod : ans;
    }

    public long fastPow(long x, long y) {
        if (y == 0) return 1;
        if (y == 1) return x;

        long temp = fastPow(x, y / 2);
        long ans = (temp * temp);

        return (y % 2 == 1) ? (ans * x) : ans;
    }

}
