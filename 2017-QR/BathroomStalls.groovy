PrintStream output = System.out;
Scanner scanner = new Scanner(System.in);
int T = scanner.nextInt();
for (int t = 1; t <= T; t++) {
  long n = scanner.nextLong();
  long k = scanner.nextLong();
  long[] pair = solve(n, k);
  output.println("Case #${t}: ${pair[0]} ${pair[1]}");
}
scanner.close();

long[] solve(long n, long k) {
  long min = n-1;
  long max = n;
  long minCount = 0;
  long maxCount = 1;
  while (k > maxCount + minCount) {
    long min2 = (min - min%2)/2 + min%2 - 1;
    long max2 = (max - max%2)/2;
    long minCount2 = (min%2 == 0) ? minCount : 2*minCount + maxCount;
    long maxCount2 = (max%2 == 0) ? maxCount : 2*maxCount + minCount;
    k -= (maxCount + minCount);
    minCount = minCount2;
    maxCount = maxCount2;
    min = min2;
    max = max2;
  }
  long size = (k - maxCount <= 0) ? max : min;
  long[] res = new long[2];
  res[0] = (size - size%2)/2;
  res[1] = (size - size%2)/2 + size%2 - 1;
  return res;
}