PrintStream output = System.out;
Scanner scanner = new Scanner(System.in);
int T = scanner.nextInt();
for (int t = 1; t <= T; t++) {
  int r = scanner.nextInt();
  int c = scanner.nextInt();
  String[] grid = new String[r];
  for (int i=0; i<r; i++) {
    grid[i] = scanner.next();
  }
  String[] answer = solve(r, c, grid);
  output.println("Case #${t}:");
  for (String row : answer) {
    output.println(row);
  }
}
scanner.close();

String[] solve(int r, int c, String[] rowGrid) {
  String[][] grid = new String[r][c];
  for (int i=0; i<r; i++) {
    for (int j=0; j<c; j++) {
      grid[i][j] = rowGrid[i][j];
    }
  }
  for (int i=0; i<r; i++) {
    String initial = '?';
    for(int j=0; j<c && initial == '?'; j++) {
      initial = grid[i][j];
    }
    if (initial == '?') {
      continue;
    }
    for(int j=0; j<c;) {
      while(j < c && grid[i][j] == '?') {
        grid[i][j++] = initial;
      }
      if (j < c) {
        initial = grid[i][j++];
      }
    }
  }
  String[] initials = new String[c];
  Arrays.fill(initials, '?');
  for(int i=0; i<r && initials[0] == '?'; i++) {
    initials = grid[i];
  }
  for(int i=0; i<r;) {
    while(i < r && grid[i][0] == '?') {
      grid[i++] = initials;
    }
    if (i < r) {
      initials = grid[i++];
    }
  }
  String[] answer = new String[r];
  for(int i=0; i<r; i++) {
    answer[i] = String.join('', grid[i]);
  }
  return answer;
}