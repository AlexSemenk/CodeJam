import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class RevengeOfThePancakes {
	
	private static PrintStream out = System.out;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	    int t = in.nextInt();
	    for (int i = 1; i <= t; i++) {
	      String line = in.next();
	      int answer = solve(line);
	      out.println("Case #" + i + ": " + answer);
	    }
	    in.close();
	}
	
	public static int solve(String s) {
		return s.replaceAll("\\+{2,}", "+").replaceAll("-{2,}", "-").replaceAll("\\+$", "").length();
	}
	
}
