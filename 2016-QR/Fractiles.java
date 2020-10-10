import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class Fractiles {

	private static PrintStream out = System.out;
	private static long[] IMPOSSIBLE = new long[0];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	    int t = in.nextInt();
	    for (int i = 1; i <= t; i++) {
			int k = in.nextInt();
			int c = in.nextInt();;
			int s = in.nextInt();
			long[] answer = solve(k, c ,s);
			out.format("Case #%d: ", i);
			if (answer == IMPOSSIBLE) {
				out.print("IMPOSSIBLE");
			} else if (answer.length > 0) {
				out.print(String.valueOf(answer[0]));
				for (int j=1; j<answer.length; j++) {
					out.print(' ');
					out.print(String.valueOf(answer[j]));
				}
			}
			out.println();
	    }
	    in.close();
	}
	
	private static long[] solve(int k, int c, int s) {
		int num = (k + (c-1)) / c;
		if (num > s) {
			return IMPOSSIBLE;
		}
		long[] answer = new long[num];
		long p0 = 1;
		for(int i=0; i<num; i++, p0 = Math.min(p0 + c, k)) {
			answer[i] = solveCase(k ,c, p0);
		}
		return answer;
	}
	
	private static long solveCase(long k, long c, long p0) {
		long tn = p0;
		long pn = p0;
		long tn1;
		long pn1;
		for(int n=2; n<=c; n++) {
			tn1 = (tn == k) ? k : tn + 1;
			pn1 = (pn - 1)*k + tn1;
			tn = tn1;
			pn = pn1;
		}
		return pn;
	}
	
}
