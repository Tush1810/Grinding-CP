import java.util.*;
import java.lang.*;
import java.math.BigInteger;

import java.io.*;

public class q4 implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new q4(), "whatever", 1 << 26).start();
    }


    private FastScanner sc;
    private PrintWriter pw;

    public void run() {
        try {
            // pw = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
            // sc = new FastScanner(new BufferedReader(new FileReader("input.txt")));
            pw = new PrintWriter(System.out);
            sc = new FastScanner(new BufferedReader(new InputStreamReader(System.in)));
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

    public void solve() {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        HashSet<Integer> set = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] <= n && !set.contains(arr[i])) {
                set.add(arr[i]);
            } else {
                pq.add(arr[i]);
                count++;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (set.contains(i)) continue;
            int temp = pq.poll();
            if (((temp) % (temp - i)) != i) {
                pw.println(-1);
                return;
            }
        }
        pw.println(count);
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
