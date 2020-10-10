import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TheLastWord_Linear {

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
		LinkedList<Character> list = new LinkedList<>();
		list.add(input.charAt(0));
		for(int i=1; i<input.length(); i++) {
			char c = input.charAt(i);
			if (c >= list.getFirst()) {
				list.addFirst(c);
			} else {
				list.addLast(c);
			}
		}
		return join(list);
	}
	
	private static String join(List<Character> list) {
		StringBuilder builder = new StringBuilder(list.size());
		for (char c : list) {
			builder.append(c);
		}
		return builder.toString();
	}
	
}
