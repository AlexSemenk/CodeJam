PrintStream output = System.out;
Scanner scanner = new Scanner(System.in);
int T = scanner.nextInt();
for (int t = 1; t <= T; t++) {
  int n = scanner.nextInt();
  int k = scanner.nextInt();
  double[] r = new int[n];
  double[] h = new int[n];
  for(int i=0; i<n; i++) {
    r[i] = scanner.nextDouble();
    h[i] = scanner.nextDouble();
  }
  double answer = solve(n, k, r, h);
  output.println("Case #${t}: ${answer}");
}
scanner.close();

double solve(int n, int k, double[] r, double[] h) {
  double max = 0;
  for (int i=0; i < n; i++) {
    double a1 = r[i] * r[i];

    double[] m = new double[n];
    for (int j=0; j<n; j++) {
      if (j == i || r[j] > r[i]) {
        m[j] = 0;
      } else {
        m[j] = r[j] * h[j];
      }
    }
    Arrays.sort(m);

    double a2 = r[i] * h[i];
    for (int j=0; j<k-1; j++) {
      a2 += m[n-1-j];
    }
    double curr = a1 + 2L * a2;
    max = Math.max(max, curr);
  }
  return max * Math.PI;
}