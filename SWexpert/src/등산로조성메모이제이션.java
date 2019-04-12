

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 등산로조성메모이제이션 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;
	static int[][] board;
	static int max = 0;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int ans = 0;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new int[N][N];

			max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, board[i][j]);
				}
			}

			ans = 0;
			memo.clear();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == max) {
						ans = Math.max(ans, dfs(j, i, 0, new boolean[N][N], true));
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static HashMap<String, Integer> memo = new HashMap<>();

	static int dfs(int x, int y, int direct, boolean[][] visited, boolean used) {
		visited[y][x] = true;
		String key = new StringBuilder().append(direct).append(used).append("x").append(x).append("y").append(y).toString();
		if (memo.containsKey(key)) {
			return memo.get(key);
		}

		int result = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;
			
			if (!visited[ny][nx]) {
				if (board[ny][nx] < board[y][x]) {
					result = Math.max(result, dfs(nx, ny, i, visited, used));
					visited[ny][nx] = false;
				} else {
					if (used) {
						if (board[ny][nx] - K < board[y][x]) {
							int tmp = board[ny][nx];
							board[ny][nx] = board[y][x] - 1;
							result = Math.max(result, dfs(nx, ny, i, visited, false));
							visited[ny][nx] = false;
							board[ny][nx] = tmp;
						}
					}
				}
			} 
		}

		++result;
		memo.put(key, result);

		return result;
	}
}
