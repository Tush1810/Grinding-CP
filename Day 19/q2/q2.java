import java.util.*;
import java.lang.*;
import java.math.BigInteger;

import java.io.*;

public class q2 implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new q2(), "whatever", 1 << 26).start();
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

        // int t = sc.nextInt();
        int t = 1;
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

    public void solve() {
        char[] arr = sc.next().toCharArray();
        int n = arr.length;
        int[] dp = new int[n + 1];
        Stack<Integer> s = new Stack<Integer>();
        for (int j = 0; j < n; j++) {
            if (s.size() > 0 && arr[j] == ')') {
                int temp = s.pop();
                dp[temp] = j;
            } else if (arr[j] == '(') {
                s.push(j);
            }
        }
        int ans = 0, tempAns = 0, freq = 1;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            if (dp[i] == 0 || set.contains(i)) continue;
            int j = dp[i] + 1;
            tempAns = j - i;
            while (dp[j] != 0) {
                // System.out.println("j=" + j + " , tempAns=" + tempAns);
                set.add(j);
                j = dp[j] + 1;
                tempAns = (j - i);
            }
            // System.out.println("i=" + i + " , tempAns=" + tempAns);
            if (tempAns > ans) {
                ans = tempAns;
                freq = 1;
            } else if (tempAns == ans) {
                freq++;
            }
        }
        pw.print(ans + " " + freq);
    }

    private long modAdd(long a, long b) {
        return (a + b) % mod;
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

