import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1861 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] board;
	static boolean[][] check;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int max = 0;
	static int maxNum = 0;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N][N];
			check = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max = 0;
			maxNum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(j, i, check, 1, board[i][j]);
					check[i][j] = false;
				}
			}

			sb.append("#").append(tc).append(" ").append(maxNum).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int x, int y, boolean[][] visited, int cnt, int startNum) {
		visited[y][x] = true;
		if (cnt > max) {
			max = cnt;
			maxNum = startNum;
		} else if (cnt == max) {
			if (maxNum > startNum)
				maxNum = startNum;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;

			if (board[ny][nx] == board[y][x] + 1 && !visited[ny][nx]) {
				dfs(nx, ny, visited, cnt + 1, startNum);
				visited[ny][nx] = false;
			}
		}
	}
}
