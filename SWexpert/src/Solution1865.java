import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution1865 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static double[][] board;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new double[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Double.parseDouble(st.nextToken()) / 100.0;
				}
			}
			max = 0;
			solve(new boolean[N], 0, 1);
			String s = String.format("%.6f", max * 100);
//			double b = Math.round(Double.parseDouble(s) * 100000) / 100000.0;
			sb.append("#").append(tc).append(" ").append(s).append("\n");
		}
		System.out.println(sb);
	}

	static double max = 0;

	static void solve(boolean[] visited, int depth, double sum) {

		if (visited.length == depth) {
			if (max < sum)
				max = sum;
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i] && sum * board[depth][i] > max) {
				visited[i] = true;
				solve(visited, depth + 1, sum * board[depth][i]);
				visited[i] = false;
			}
		}
	}
}
