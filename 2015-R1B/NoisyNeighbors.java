import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class NoisyNeighbors {
	
	private static final File INPUT = new File("d:/downloads/test.in");
	private static final File OUTPUT = new File("d:/downloads/test.out");
	
	public static void main(String[] args) throws Exception {
		evel();
	}

	public static boolean compare(int I, int J) {
		for(int i=1; i<I; i++) {
			for(int j=1; j<J; j++) {
				for(int k=0; k<=i*j; k++) {
					int r1 = longEvel(i, j, k);
					int r2 = evel(i, j, k);
					if (r1 != r2) {
						System.out.println("Not Comparable:");
						System.out.println("R="+i+" C="+j+" N="+k);
						System.out.println("Real: " + r1 + ";");
						System.out.println("My: " + r2 + "\n");
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static void evel() throws Exception {
		Scanner sc = new Scanner(new FileInputStream(INPUT));
		OutputStream output = new FileOutputStream(OUTPUT);
		OutputStreamWriter writer = new OutputStreamWriter(output);
		int testNum = sc.nextInt();
		for (int i = 1; i <= testNum; i++) {
			int R = sc.nextInt();
			int C = sc.nextInt();
			int N = sc.nextInt();
			int A = evel(R, C, N);
			writer.write("Case #" + i + ": " + A);
			if (i != testNum) {
				writer.write('\n');
			}
		}
		sc.close();
		writer.close();
	}

	private static int R;
	private static int C;
	private static int N;
	private static boolean[][] rect;
	
	public static int longEvel(int R, int C, int N) {
		Main.R = R;
		Main.C = C;
		Main.N = N;
		rect = new boolean[R][C];
		return longEvel(0, 0);
	}
	
	public static int longEvel(int I, int n) {
		if (n == N)
			return longCheck();
		if (N-n > C*R - I)
			return R*C;
		if (N-n == C*R - I) {
			setLeft(I, true);
			int r = longCheck();
			setLeft(I, false);
			return r;
		}
		int i=I/C;
		int j=I%C;
		rect[i][j] = true;
		int r1 = longEvel(I+1, n+1);
		rect[i][j] = false;
		int r2 = longEvel(I+1, n);
		return min(r1, r2);
	}
	
	public static void setLeft(int I, boolean v) {
		for(int j=I%C; j<C; j++) {
			rect[I/C][j] = v;
		}
		for(int i=I/C+1; i<R; i++) {
			for(int j=0; j<C; j++) {
				rect[i][j] = v;
			}
		}
	}
	
	public static int longCheck() {
		int c = 0;
		for (int i=0; i<R-1; i++) {
			for(int j=0; j<C; j++) {
				c += rect[i][j] && rect[i+1][j] ? 1 : 0;
			}
		}
		for (int j=0; j<C-1; j++) {
			for(int i=0; i<R; i++) {
				c += rect[i][j] && rect[i][j+1] ? 1 : 0;
			}
		}
		return c;
	}
	
	public static int evel(int R, int C, int N) {
		if (R == 1 || C == 1) {
			if (R == 1) {
				R = C;
				C = 1;
			}
			if (R%2 == 0) {
				return 1 * max(min(N - R/2, 1), 0) + 2 * max(N - R/2 - 1, 0);
			} else {
				return 2 * max(N - (R+1)/2, 0);
			}
		}
		if (R % 2 == 0 || C % 2 == 0) {
			int A = R * C / 2;
			int G = (2 * R + 2 * C - 4) / 2;
			return 2 * max(min(N - A, 2), 0) + 3 * max(min(N - A - 2, G -2), 0) + 4 * max(N - A - G, 0);
		} else {
			int A1 = R * C / 2;
			int A2 = A1 + 1;
			if (A2 + 2 >= N) {
				return 3 * max(N-A2, 0);
			} else {
				int G = (2 * R + 2 * C - 4) / 2;
				return 2 * max(min(N - A1, 4), 0) + 3 * max(min(N - A1 - 4, G-4), 0) + 4 * max(N - A1 - G, 0);
			}
		}
	}

}