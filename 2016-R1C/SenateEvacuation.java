import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SenateEvacuation {

	private static PrintStream out = System.out;

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; i++) {
			int n = in.nextInt();
			int[] size = new int[n];
			for (int j = 0; j < n; j++) {
				size[j] = in.nextInt();
			}
			List<Character> answer = solve(size);
			out.println("Case #" + i + ": " + toString(answer));
		}
		in.close();
	}
	
	public static List<Character> solve(int size[]) {
		List<Character> output = new LinkedList<Character>();
		int pos = -1;
		while((pos = max(size)) >= 0) {
			output.add(alphabet(pos));
			size[pos]--;
		}
		return output;
	}
	
	public static int max(int[] arr) {
		int max = 0;
		int pos = -1;
		for(int i =0; i<arr.length; i++) {
			if (arr[i] > max) {
				pos = i;
				max = arr[i];
			}
		}
		return pos;
	}
	
	public static Character alphabet(int idx) {
		return (char)(idx + 'A');
	}
	
	public static String toString(List<Character> output) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for(Character c : output) {
			if (i != 0 && i % 2 == 0) {
				sb.append(' ');
			}
			sb.append(c);
			i++;
		}
		return sb.toString();
	}
	
}
