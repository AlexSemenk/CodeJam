import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @author Alexander Semenkevich
 * @see https://code.google.com/codejam/contest/11254486/dashboard
 */
public class GettingTheDigits {

	private static PrintStream out = System.out;

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; i++) {
			String input = in.next();
			String output = solve(input);
			out.println("Case #" + i + ": " + output);
		}
		in.close();
	}
	
	public static String solve(String input) {
		Map<Integer, Long> counts = input.chars().boxed().collect(groupingBy(identity(), counting()));
		long[] numbers = new long[10];
		numbers[0] = solve(counts, 'Z', "ZERO");
		numbers[2] = solve(counts, 'W', "TWO");
		numbers[4] = solve(counts, 'U', "FOUR");
		numbers[6] = solve(counts, 'X', "SIX");
		numbers[8] = solve(counts, 'G', "EIGHT");
		numbers[7] = solve(counts, 'S', "SEVEN");
		numbers[5] = solve(counts, 'V', "FIVE");
		numbers[3] = solve(counts, 'R', "THREE");
		numbers[1] = solve(counts, 'O', "ONE");
		numbers[9] = solve(counts, 'E', "NINE");
		StringBuilder res = new StringBuilder();
		for(int i=0; i<10; i++) {
			for(long j=0; j<numbers[i]; j++) {
				res.append(i);
			}
		}
		return res.toString();
	}
	
	public static long solve(Map<Integer, Long> counts, int character, String number) {
		if (!contains(counts, character)) {
			return 0L;
		} else {
			Long count = counts.get(character);
			for(char c : number.toCharArray()) {
				remove(counts, c, count);
			}
			return count;
		}
	}
	
	public static boolean contains(Map<Integer, Long> map, Integer key) {
		return map.containsKey(key) && map.get(key) != 0;
	}
	
	public static void remove(Map<Integer, Long> map, int character, Long count) {
		Long value = map.get(character);
		if(value.equals(count)) {
			map.remove(character);
		} else {
			map.put(character, value - count);
		}
	}
	
}
