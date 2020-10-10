import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @see https://code.google.com/codejam/contest/4314486/dashboard#s=p1
 * doesn't work yet!
 */
public class Slides {

	private static PrintStream out = System.out;

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; i++) {
			int b = in.nextInt();
			long m = in.nextLong();
			if (m > pow2(b-2)) {
			    out.println("Case #" + i + ": IMPOSSIBLE");
			} else {
				out.println("Case #" + i + ": POSSIBLE");
				boolean[][] answer = solve(b, m);
				println(answer);
			}
		}
		in.close();
	}
	
	public static boolean[][] solve(int b, long m) {
		boolean[][] out = new boolean[b][b];
		if (m == 0L)
			return out;
		m = ((m-1L)<<1) | 1L;
		for(int j=0; m != 0; j++, m >>= 1) {
			for(int i=0; i<j; i++)
				out[i][j] = true;
			if ((m & 1L) == 1L)
				out[j][b-1] = true;
		}
		return out;
	}

	private static long pow2(long n) {
		return 1 << n;
	}
	
	private static void println(boolean[][] matr) {
		for(int i=0; i<matr.length; i++) {
			for(int j=0; j<matr[i].length; j++) {
				out.print(matr[i][j] ? "1" : "0");
			}
			out.println();
		}
	}
	
}
