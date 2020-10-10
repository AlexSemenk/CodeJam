import static java.util.Arrays.sort;

PrintStream output = System.out;
Scanner scanner = new Scanner(System.in);
int T = scanner.nextInt();
for (int t = 1; t <= T; t++) {
  int ac = scanner.nextInt();
  int aj = scanner.nextInt();
  int[] c = new int[ac];
  int[] d = new int[ac];
  int[] j = new int[aj];
  int[] k = new int[aj];
  for (int i=0; i<ac; i++) {
    c[i] = scanner.nextInt();
    d[i] = scanner.nextInt();
  }
  for (int i=0; i<aj; i++) {
    j[i] = scanner.nextInt();
    k[i] = scanner.nextInt();
  }
  int answer = solve(ac, aj, c, d, j, k);
  output.println("Case #${t}: ${answer}");
}
scanner.close();

int solve(int ac, int aj, int[] c, int[] d, int[] j, int[] k) {
  int UNDEFINED = 0;
  int CAMERON = 1;
  int JAMIE = 2;
  int a = ac + aj;

  int[] b = new int[a]; // beg
  int[] e = new int[a]; // end
  int[] n = new int[a]; // name
  for (int i=0, ci = 0, ji = 0; i<a; i++) {
    if (c[ci] < d[di]) {
      b[i] = c[ci];
      e[i] = d[ci];
      n[i] = JAMIE;
      ci++;
    } else {
      b[i] = j[cj];
      e[i] = k[cj];
      n[i] = CAMERON;
      cj++;
    }
  }

  List<Integer> jg = new ArrayList<>(a+1);
  List<Integer> cg = new ArrayList<>(a+1);
  int xg = 0;
  for (int i=1; i<a; i++) {
    int g = b[i] - e[i-1];
    if (n[i-1] == CAMERON && n[i] == CAMERON) {
      cg.add(g);
    } else if (n[i-1] == JAMIE && n[i] == JAMIE) {
      jg.add(g);
    } else {
      xg += g;
    }
  }

  Collections.sort(jg);
  Collections.sort(cg, Collections.reverseOrder());

  int n = 2;
  int t = 0;
  for(int i=0; i<n.length; i++) {
    if (n[i] == JAMIE) {
      t += e[i] - b[i];
    }
  }
  int jgi = 0;
  while (jgi < jg.length && t < 12 * 60) {
    t += jg.get(jgi++);
  }
  if (t == 12 * 60) {
    n += 2 * (jg.length - jgi);
    return n;
  }
  if (t > 12 * 60) {
    n += 2 * (jg.length - jgi);
    return n;
  }
  // j gaps -> sort asc
  // c gaps -> sort desc
  // x gaps -> sum

  int[] cg = new int[a+1];
  int[] jg = new int[a+1];
  int ct = 0;
  int jt = 0;
  int g0 = b[0];
  int t0 = e[0];
  if (n[0] == CAMERON) {
    ct += t0;
    cg[0] = g0;
  }
  if (n[0] == JAMIE) {
    cj += t0;
    jg[0] = g0;
  }
  int num = 2;
  for (int i=1; i<a; i++) {
    int t = b[i] - e[i];
    if (n[i] == n[i-1]) {
      t += b[i] - e[i-1];
    } else {
      num += 2;
    }
    if (n[i] == CAMERON) {
      ct += t;
    }
    if (n[i] == JAMIE) {
      cj += t;
    }
  }

  int t = 0;
  int[] g = new int[0];
  if (ct > 12 * 60) {
    t = ct;
    g = cg;
  }
  if (jt > 12 * 60) {
    t = jt;
    g = jg;
  }
  reverse(sort(g));
  for(int i=0; t > 12 * 60; i++) {
    t -= g[i];
    num += 2;
  }

  return num;
}

void reverse(int[] arr) {
  for(int i=0, j=arr.length-1; i < j; i++, j--) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}