import static java.util.stream.Collectors.*;
import static java.util.Collections.emptyList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @see https://code.google.com/codejam/contest/4304486/dashboard#s=p2
 */
public class BFFs_Linear {

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

	public static int[] range(int f, int l) {
		int[] res = new int[l-f+1];
		for(int i=0, v=f; i<res.length; i++, v++) {
			res[i] = v;
		}
		return res;
	}
	
	public static int solve(int n, int[] bff) {
		dec(bff);
		return Math.max(solveForPairs(n, bff), solveForCycles(n, bff));
	}
	
	@SuppressWarnings("unchecked")
	public static int solveForPairs(int n, int[] bff) {
		int[] kds = range(0, n-1); /* all kids */
		Map<Integer, List<Integer>> bffMap = Arrays.stream(kds).boxed().collect(groupingBy(i -> bff[i]));
		List<Integer>[] bffArr = (List<Integer>[])Arrays.stream(kds).boxed()
				.map(i -> (bffMap.containsKey(i) ? bffMap.get(i) : emptyList()))
				.collect(toList())
				.toArray(new List[0]);
		return Arrays.stream(kds)
				.filter(i -> (bff[bff[i]] == i))
				.map(i -> {
					int h = 1;
					List<Integer> curr = bffArr[i].stream().filter(j -> bff[bff[j]] != j).collect(toList());
					while(!curr.isEmpty()) {
						curr = curr.stream().map(j -> bffArr[j]).flatMap(j -> j.stream()).collect(toList());
						h++;
					}
					return h;
				}).sum();
	}
	
	public static int solveForCycles(int n, int[] bff) {
		Set<Integer> sbf = sbf(bff);
		LinkedList<Integer> res = new LinkedList<>();
		while(!sbf.isEmpty()) {
			int len = 0;
			int f = sbf.iterator().next();
			sbf.remove(f);
			int l = f;
			do {
				len++;
				l = bff[l];
				sbf.remove(l);
			} while (f != l);
			res.add(len);
		}
		return max(res, 0);
	}

	public static int max(List<Integer> list, int def) {
		int max = def;
		for(Integer i : list) {
			max = Math.max(max, i);
		}
		return max;
	}
	
	/* set of kids where somebody is friend at least of someone */
	public static Set<Integer> sbf(int[] bff) {
		List<Integer>[] aff = aff(bff.length, bff);
		Set<Integer> sbf = new HashSet<>();
		for(int i=0; i<aff.length; i++) {
			sbf.add(i);
		}
		int prevSize = 0;
		do {
			prevSize = sbf.size();
			LinkedList<Integer> rem = new LinkedList<>();
			for (Integer i : sbf) {
				if (aff[i].isEmpty()) {
					rem.add(i);
					aff[bff[i]].remove(i);
				}
			}
			for(Integer i : rem) {
				sbf.remove(i);
			}
		} while(prevSize != sbf.size());
		return sbf;
	}

	@SuppressWarnings("unchecked")
	public static List<Integer>[] aff(int n, int bff[]) {
		List<Integer>[] arr = new List[n];
		for (int i = 0; i < n; i++) {
			arr[i] = new LinkedList<>();
		}
		for (int i = 0; i < n; i++) {
			arr[bff[i]].add(i);
		}
		return arr;
	}

	public static void dec(int[] bff) {
		for (int i = 0; i < bff.length; i++) {
			bff[i] = bff[i] - 1;
		}
	}

}