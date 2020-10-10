import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RankAndFile {

	private static PrintStream out = System.out;

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; i++) {
			int n = in.nextInt();
			Set<Integer> set = new HashSet<>();
			for(int j=0; j<n*(2*n-1); j++) {
				int h = in.nextInt();
				if (set.contains(h)) {
					set.remove(h);
				} else {
					set.add(h);
				}
			}
			String answer = set.stream().sorted().map(String::valueOf).collect(joining(" "));
			out.println("Case #" + i + ": " + answer);
		}
		in.close();
	}
	
}
