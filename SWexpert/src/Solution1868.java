
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Solution1868 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int[][] board;
	static boolean[][] check;
	static final int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static final int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };
	static int N;
	static Queue<Point> q = new LinkedList<>();
	static Queue<Point> bfs_q = new LinkedList<>();

	public static void write_num(int x, int y) {
		int cnt = 0;
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;

			if (board[ny][nx] == -2)
				cnt++;
		}
		board[y][x] = cnt;
		if (board[y][x] == 0) {
			q.offer(new Point(x, y));
		}
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N][N];
			check = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String tmp = br.readLine().trim();
				for (int j = 0; j < N; j++) {
					board[i][j] = tmp.charAt(j);
					if (board[i][j] == '*') {
						board[i][j] = -2;
						check[i][j] = true;
					} else {
						board[i][j] = -1;
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == -1) {
						write_num(j, i);
					}
				}
			}
			int result = 0;
			while (!q.isEmpty()) {
				Point p = q.poll();
				if (!check[p.y][p.x]) {
					result++;
					check[p.y][p.x] = true;
					bfs_q.offer(new Point(p.x, p.y));
					while (!bfs_q.isEmpty()) {
						Point bfs_p = bfs_q.poll();
						for (int i = 0; i < 8; i++) {
							int nx = bfs_p.x + dx[i];
							int ny = bfs_p.y + dy[i];

							if (nx < 0 || nx >= N || ny < 0 || ny >= N)
								continue;
							if (!check[ny][nx]) {
								check[ny][nx] = true;
								if (board[ny][nx] == 0) {
									bfs_q.offer(new Point(nx, ny));
								}
							}
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!check[i][j])
						result++;
				}
			}
			bw.write(new StringBuilder("#").append(tc).append(" ").append(result).append("\n").toString());
		}
		bw.flush();
		bw.close();
	}

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
