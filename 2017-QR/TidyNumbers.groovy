PrintStream output = System.out;
Scanner scanner = new Scanner(System.in);
int T = scanner.nextInt();
for (int t = 1; t <= T; t++) {
  String n = scanner.next();
  String answer = solve(n);
  output.println("Case #${t}: ${answer}");
}
scanner.close();

String solve(String n) {
  String[] arr = n.split("");
  int last = arr.length;
  for (int i = arr.length - 1; i > 0; i--) {
    int a = Integer.parseInt(arr[i-1]);
    int b = Integer.parseInt(arr[i]);
    if (a > b) {
      arr[i-1] = Integer.toString(a - 1);
      for (int j = i; j < last; j++) {
        arr[j] = '9';
      }
      last = i;
    }
  }
  return Long.parseLong(String.join('', arr)).toString();
}