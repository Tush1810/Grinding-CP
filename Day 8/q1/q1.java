import java.util.*;
import java.lang.*;
import java.math.BigInteger;

import java.io.*;

public class q1 implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new q1(), "whatever", 1 << 26).start();
    }


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
        long first, second;
        Pair(long first, long second) {
            this.first = first;
            this.second = second + 1l;
        }
    }
    public void solve() {
        int n = sc.nextInt();
        Pair[] arr = new Pair[n];
        long[] prefix = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(sc.nextLong(), (long)i);
        }


        Sorter.sort(arr, (o1, o2)-> {
            if (o1.first > o2.first) return 1;
            if (o2.first > o1.first) return -1;
            return 0;
        });
        prefix[0] = arr[0].first;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + arr[i].first;
        }

        ////// GREEDY
        // int last = -1;
        // for (int i = 0; i < n - 1; i++) {
        //     if (prefix[i] < arr[i + 1].first) {
        //         last = i;
        //     }
        // }
        // last++;

        ////// BINARY SEARCH
        int start = 0, end = n - 1, mid;
        int last = n - 1;
        PriorityQueue<Long> pq = new PriorityQueue<Long>();
        while (start <= end) {
            mid = (start + end) >> 1;
            // System.out.println("start=" + start + " , end=" + end + " , mid=" + mid);
            boolean found = false;
            for (int i = mid; i < last; i++) {
                if (prefix[i] < arr[i + 1].first) {
                    found = true;
                    break;
                }
            }
            if (found) {
                start = mid + 1;
            } else {
                end = mid - 1;
                last = mid;
            }
        }
        // System.out.println("last=" + last);
        pq.add(arr[last].second);
        for (int j = last - 1; j >= 0; j--) {
            if (arr[j].first == arr[last].first) {
                pq.add(arr[j].second);
            } else break;
        }
        for (int j = last + 1; j < n; j++) {
            pq.add(arr[j].second);
        }
        pw.println(pq.size());
        while (pq.size() != 0) {
            pw.print(pq.poll() + " ");
        }
        pw.println();
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