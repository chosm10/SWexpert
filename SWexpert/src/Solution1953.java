import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1953 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int X, Y, startX, startY, L;
	static int[][] board;
	static boolean[][] visited;
	static boolean[][] hasu = { {}, { true, true, true, true }, { false, true, false, true },
			{ true, false, true, false }, { false, true, true, false }, { false, false, true, true },
			{ true, false, false, true }, { true, true, false, false } };
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int[] reverse = { 2, 3, 0, 1 };
	static Queue<Point> q = new LinkedList<>();

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			Y = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			startX = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			board = new int[Y][X];
			visited = new boolean[Y][X];

			for (int i = 0; i < Y; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < X; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			visited[startY][startX] = true;
			q.offer(new Point(startX, startY));

			int cnt = 0;
			int result = 0;
			while (!q.isEmpty()) {
				int qsize = q.size();
				if (cnt == L)
					break;
				for (int i = 0; i < qsize; i++) {
					Point p = q.poll();
					result++;
					for (int j = 0; j < 4; j++) {
						int nx = p.x + dx[j];
						int ny = p.y + dy[j];

						if (nx < 0 || nx >= X || ny < 0 || ny >= Y)
							continue;

						if (board[ny][nx] != 0) {
							if (!visited[ny][nx]) {
								int nowHasu = board[p.y][p.x];
								int nextHasu = board[ny][nx];
								if (hasu[nowHasu][j] && hasu[nextHasu][reverse[j]]) {
									visited[ny][nx] = true;
									q.offer(new Point(nx, ny));
								}
							}
						}
					}
				}
				cnt++;
			}
			q.clear();
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}
