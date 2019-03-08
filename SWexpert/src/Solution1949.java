import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1949 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;
	static int[][] board;
	static boolean[][] check;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int maxRoad;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			// N K
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new int[N][N];
			check = new boolean[N][N];

			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (max < board[i][j])
						max = board[i][j];
				}
			}
			maxRoad = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == max) {
						dfs(j, i, check, true, 1);
						check[i][j] = false;
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(maxRoad).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int x, int y, boolean[][] visited, boolean flag, int cnt) {
		visited[y][x] = true;
		if (maxRoad < cnt)
			maxRoad = cnt;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;

			if (!visited[ny][nx]) {
				if (board[ny][nx] < board[y][x]) {
					dfs(nx, ny, visited, flag, cnt + 1);
					visited[ny][nx] = false;
				} else {
					if (flag) {
						if (board[ny][nx] - K < board[y][x]) {
							int tmp = board[ny][nx];
							board[ny][nx] = board[y][x] - 1;
							dfs(nx, ny, visited, !flag, cnt + 1);
							visited[ny][nx] = false;
							board[ny][nx] = tmp;
						}
					}
				}
			}
		}
	}
}
