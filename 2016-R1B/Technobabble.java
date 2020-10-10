import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Alexander Semenkevich
 * @see https://code.google.com/codejam/contest/11254486/dashboard#s=p2
 */
public class Technobabble {

	private static PrintStream out = System.out;

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; i++) {
			int n = in.nextInt();
			List<String> list1 = new LinkedList<>();
			List<String> list2 = new LinkedList<>();
			for(int j=0; j<n; j++) {
				list1.add(in.next());
				list2.add(in.next());
			}
			int answer = solve(list1, list2);
     		out.println("Case #" + i + ": " + answer);
		}
		in.close();
	}
	
	private static int solve(List<String> list1, List<String> list2) {
		int fake = 0;
		Set<String> original1 = new HashSet<>();
		Set<String> unique1 = new HashSet<>();
		Set<String> original2 = new HashSet<>();
		Set<String> unique2 = new HashSet<>();
		for(String s1 : list1) {
			if(!original1.contains(s1)) {
				original1.add(s1);
				unique1.add(s1);
			} else if (unique1.contains(s1)) {
				unique1.remove(s1);
			}
		}
		for(String s2 : list2) {
			if(!original2.contains(s2)) {
				original2.add(s2);
				unique2.add(s2);
			} else if (unique2.contains(s2)) {
				unique2.remove(s2);
			}
		}
		Iterator<String> it1 = list1.listIterator();
		Iterator<String> it2 = list2.listIterator();
		while(it1.hasNext() && it2.hasNext()) {
			String next1 = it1.next();
			String next2 = it2.next();
			if (unique1.contains(next1) | unique2.contains(next2)) {
				it1.remove();
				it2.remove();
				unique1.add(next1);
				unique2.add(next2);
			}
		}
//		it1 = list1.listIterator();
//		it2 = list2.listIterator();
//		while(it1.hasNext() && it2.hasNext()) {
//			String next1 = it1.next();
//			String next2 = it2.next();
//			if (unique1.contains(next1) | unique2.contains(next2)) {
//				it1.remove();
//				it2.remove();
//				unique1.add(next1);
//				unique2.add(next2);
//			}
//		}
		Set<String> set1 = new HashSet<>(list1);
		Set<String> set2 = new HashSet<>(list2);
		return list1.size() - Math.max(set1.size(), set2.size());
	}
	
}
