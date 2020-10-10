import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CounterCulture {
	
	public static void main(String[] args) throws IOException {
		solveFolder(INPUT);
	}
	
	public static long generalCounter(long num) {
		if (num <= 19) {
			return num;
		}
		int sep = (length(num)) / 2;
		long first = sub(num, 0, sep);
		long last = sub(num, sep, length(num));
		if (isPowOfTen(first)) {
			return perfectCounter(length(num)) + last;
		}
		else {
			first = reverse(sub(num, 0, sep));
			last = sub(num, sep, length(num));
			if (last != 0)  {
				return (long)perfectCounter(length(num)) + first + 1 + last - 1;
				// ... -> 100...000 -> 100...cba -> abc...001 -> abc...def
			} else {
				first = sub(num, 0, sep) - 1;
				last = nineSeq(length(num) - sep);
				if (isPowOfTen(first)) {
					return perfectCounter(length(num)) + last + 1;
				} else {
					first = reverse(first);
					return (long)perfectCounter(length(num)) + first + 1 + last - 1 + 1;
				}
				// ... -> 100...000 -> 100...(c-1)ba -> ab(c-1)...001 -> ab(c-1)...999 -> abc...000
			}
		}
	}
	
	public static boolean isPowOfTen(long value) {
		while (value % 10 == 0) {
			value /= 10;
		}
		return value == 1;
	}
	
	public static int length(long value) {
		return String.valueOf(value).length();
	}
	
	public static long reverse(long value) {
		String revStr = new StringBuilder(Long.toString(value)).reverse().toString();
		return Long.valueOf(revStr);
	}
	
	public static long sub(long value, int beg, int end) {
		String valueStr = Long.toString(value);
		return Long.valueOf(valueStr.substring(beg, end));
	}
	
	public static long nineSeq(int times) {
		StringBuilder builder = new StringBuilder(times);
		for (int i=0; i<times; i++) {
			builder.append('9');
		}
		return Long.valueOf(builder.toString());
	}
	
	public static int perfectCounter(int len) {
		if (len == 1) { // 1
			return 1;
		}
		else if (len == 2) { // 10
			return 10;
		} else {
			int n = len;
			int sum = perfectCounter(2) - (n-2) + 10;
			int pow = 10;
			int sum1 = 0;
			for (int i=3; i<n; i++) {
				if(i%2==0)
					pow *= 10;
				sum1 += pow;
			}
			sum1 *= 2;
			sum += sum1;
			if (n%2 == 0)
				pow *= 10;
			sum += pow;
			return sum;
		}
	}
	
	private static final File INPUT = new File("d:/downloads/");

	public static void solveFolder(File folder) throws IOException {
		if(!folder.isDirectory())
			throw new RuntimeException("Input folder dosen't exist");
		for(File file : folder.listFiles(f -> f.getName().endsWith(".in"))) {
			solveFile(file);
		}
	}
	
	public static String getSimpleFileName(String fileName) {
		return fileName.replaceAll("\\.([^\\.]*)$", "");
	}
	
	public static void solveFile(File inputFile) throws IOException {
		File outputFile = new File(getSimpleFileName(inputFile.getAbsolutePath())+".out");
		BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
		PrintWriter outputWriter = new PrintWriter(outputFile);
		int testNum = Integer.parseInt(inputReader.readLine());
		for (int i=0; i<testNum; i++) {
			Long n = Long.valueOf(inputReader.readLine());
			Long answer = generalCounter(n);
			outputWriter.format("Case #%d: %d\n", (i+1), answer);
		}
		inputReader.close();
		outputWriter.close();
	}
	
}