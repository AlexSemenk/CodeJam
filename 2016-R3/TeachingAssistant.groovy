PrintStream output = System.out;
Scanner scanner = new Scanner(System.in);
int T = scanner.nextInt();
for (int t = 1; t <= T; t++) {
  String task = scanner.next();
  Integer answer = solve(task);
  output.println("Case #${t}: ${answer}");
}
scanner.close();

Integer solve(String input) {
  int score = 0;
  Stack<String> stack = new Stack<>(); 
  String[] problems = input.split("");
  for(String problem : problems) {
    if (stack.isEmpty()) {
      stack.push(problem);
    } else if (stack.peek().equals(problem)) {
      score += 10;
      stack.pop();
    } else {
      stack.push(problem);
    }
  }
  score += stack.size() / 2 * 5;
  return score;
}