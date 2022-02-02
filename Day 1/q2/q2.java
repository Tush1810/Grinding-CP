import java.util.*;
import java.lang.*;
import java.math.BigInteger;

import java.io.*;

public class Main implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new Main(), "whatever", 1 << 26).start();
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
        int k = sc.nextInt();

        String s = sc.next();
        int[] arr = new int[n];
        int[] freq = new int[26];
        int[] freq2 = new int[26];
        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i) - 'a';
            freq2[arr[i]]++;
        }
        int start = 1, end = (n / k);
        int ans = 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            // System.out.println("start=" + start + " , end=" + end + " , mid = " + mid);
            for (int i = 0; i < 26; i++) {
                freq[i] = freq2[i];
            }
            if (possible(mid, k, freq)) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        pw.println(ans);
    }

    private boolean possible(int mid, int k, int[] freq) {
        for (int i = 0; i < k; i++) {
            int count = 0;
            if (mid % 2 == 1) {
                int last = -1;
                for (int j = 0; j < 26; j++) {
                    if (freq[j] == 0) {
                        continue;
                    }
                    int temp;
                    if (freq[j] % 2 == 0) {
                        last = j;
                    } else {
                        count++;
                        freq[j]--;
                        // System.out.println("mid = " + mid + " , count = " + count + " j = " + ((char)(j + 'a')));
                        break;
                    }
                }
                if (count == 0) {
                    if (last == -1) return false;
                    count++;
                    freq[last]--;
                    // System.out.println("mid = " + mid + " , count = " + count + " j = " + ((char)(last + 'a')));
                }
            }
            for (int j = 0; j < 26; j++) {
                if (freq[j] == 0) {
                    continue;
                }
                int temp;
                if (freq[j] % 2 == 0) {
                    temp = (freq[j]);
                    if (mid - count < temp) {
                        temp = mid - count;
                    }
                } else {
                    temp = (freq[j] - 1);
                    if (mid - count < temp) {
                        temp = mid - count;
                    }
                }
                count += temp;
                // System.out.println("mid = " + mid + " , count = " + count + " j = " + ((char)(j + 'a')));
                freq[j] -= temp;
                if (count == mid) {
                    break;
                }
            }
            if (count != mid) {
                return false;
            }
        }
        return true;
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
