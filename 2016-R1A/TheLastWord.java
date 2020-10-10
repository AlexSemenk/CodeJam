import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class TheLastWord {

	private static PrintStream out = System.out;

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; i++) {
			String input = in.next();
			String answer = solve(input);
			out.println("Case #" + i + ": " + answer);
		}
		in.close();
	}

	public static String solve(String input) {
		String res = "";
		for(String c : input.split("")) {
			String left  = c + res;
			String right = res + c;
			res = max(left, right);
		}
		return res;
	}
	
	private static String max(String s1, String s2) {
		return s1.compareTo(s2) >= 0 ? s1 : s2;
	}
	
}
