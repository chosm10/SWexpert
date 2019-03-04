import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution3421 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] notEatTogether;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			boolean[] set = new boolean[N + 1];

			notEatTogether = new int[N + 1][N + 1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				notEatTogether[a][b] = 1;
				notEatTogether[b][a] = 1;
			}
			ans = 0;
			power(set, 1);

			result.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		bw.write(result.toString());
		bw.flush();
		bw.close();
	}

	static int ans = 0;

	static void power(boolean[] set, int depth) {
		if (depth == set.length) {
			ans++;
			return;
		}
		boolean flag = true;
		for (int i = 1; i < depth; i++) {
			if (set[i]) {
				if (notEatTogether[i][depth] == 1)
					flag = false;
			}
		}
		if (flag) {
			set[depth] = false;
			power(set, depth + 1);
			set[depth] = true;
			power(set, depth + 1);
		} else {
			set[depth] = false;
			power(set, depth + 1);
		}
	}
}
