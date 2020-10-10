import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @see https://code.google.com/codejam/contest/4304486/dashboard#s=p2
 */
public class BFFs {

	private static PrintStream out = System.out;

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; i++) {
			int n = in.nextInt();
			int[] bff = new int[n];
			for (int j = 0; j < n; j++) {
				bff[j] = in.nextInt();
			}
			int answer = solve(n, bff);
			out.println("Case #" + i + ": " + answer);
		}
		in.close();
	}
	
	public static int solve(int n, int[] bff) {
		dec(bff);
		int[] pairs = new int[n];
		int cycle = 0;
		for(int i=0; i<n; i++) {
			int j = i;
			boolean[] visited = new boolean[n];
			visited[j] = true;
			while(!visited[bff[j]]) {
				j = bff[j];
				visited[j] = true;
			}
			if (bff[bff[j]] == j) {
				int len = pathLength(bff, i, j) - 1;
				pairs[bff[j]] = Math.max(pairs[bff[j]], len);
			} else {
				int len = pathLength(bff, bff[j], j);
				cycle = Math.max(cycle, len);
			}
		}
		int pair = sum(pairs);
		return Math.max(pair, cycle);
	}
	
	private static void dec(int[] bff) {
		for (int i = 0; i < bff.length; i++) {
			bff[i] = bff[i] - 1;
		}
	}

	private static int pathLength(int[] bff, int first, int last) {
		int length = 1;
		for(int current = first; current != last; current = bff[current]) {
			length++;
		}
		return length;
	}
	
	private static int sum(int[] arr) {
		int sum = 0;
		for(int itm : arr) {
			sum += itm;
		}
		return sum;
	}
	
}
