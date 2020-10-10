PrintStream output = System.out;
Scanner scanner = new Scanner(System.in);
int T = scanner.nextInt();
for (int t = 1; t <= T; t++) {
  String[] s = scanner.next().split("");
  Integer k = scanner.nextInt();
  String answer = solve(s, k);
  output.println("Case #${t}: ${answer}");
}
scanner.close();

def solve(String[] s, Long k) {
  String HAPPY_SIDE = "+";
  String BLANK_SIDE = "-";
  int count = 0;
  for (int i = 0; i < s.length - k + 1; i++) {
    if (s[i] == BLANK_SIDE) {
      count++;
      for (int j = i; j < i + k; j++) {
        s[j] = (s[j] == BLANK_SIDE) ? HAPPY_SIDE : BLANK_SIDE;
      }
    }
  }
  for (int i = Math.max(s.length - k + 1, 0); i < s.length; i++) {
    if (s[i] == BLANK_SIDE) {
      return "IMPOSSIBLE";
    }
  }
  return count;
}