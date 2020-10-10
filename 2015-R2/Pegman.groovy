PrintStream output = System.out;
Scanner scanner = new Scanner(System.in);
int T = scanner.nextInt();
for (int t = 1; t <= T; t++) {
  int r = scanner.nextLong();
  int c = scanner.nextLong();
  String[] field = new String[r];
  for (int i=0; i<r; i++) {
    field[i] = scanner.next();
  }
  def answer = solve(r, c, field);
  output.println("Case #${t}: ${answer}");
}
scanner.close();

def solve(int r, int c, String[] field) {
  int count = 0;
  for (int i=0; i<r; i++) {
    for (int j=0; j<c; j++) {
      if (field[i][j] == '.') {
        continue;
      }
      def dir = ['^':false, 'v':false, '<': false, '>':false];
      for (int k=0; k<i && !dir['^']; k++) {
        dir['^'] = (field[k][j] != '.');
      }
      for (int k=i+1; k<r && !dir['v']; k++) {
        dir['v'] = (field[k][j] != '.');
      }
      for (int k=0; k<j && !dir['<']; k++) {
        dir['<'] = (field[i][k] != '.');
      }
      for (int k=j+1; k<c && !dir['>']; k++) {
        dir['>'] = (field[i][k] != '.');
      }
      if (!dir['^'] && !dir['v'] && !dir['<'] && !dir['>']) {
        return "IMPOSSIBLE";
      } else if (!dir[field[i][j]]) {
        count++;
      }
    }
  }
  return count;
}