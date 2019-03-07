import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1258 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] board;
	static boolean[][] visited;
	static Queue<int[]> q;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N;
	static ArrayList<Matrix> matrixs;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] == 0)
						visited[i][j] = true;
				}
			}
			matrixs = new ArrayList<>();
			q = new LinkedList<>();
			// bfs해서 x, y 좌표가 가장 작은 점이랑 큰점을 만들어가 끝나면 그거 차로 크기랑 길이 있는 객체만들어 리스트에 넣고 소팅
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						bfs(j, i);
						cnt++;
					}
				}
			}

			Collections.sort(matrixs);
			sb.append("#").append(tc).append(" ").append(cnt).append(" ");
			for (Matrix m : matrixs) {
				sb.append(m.y).append(" ").append(m.x).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void bfs(int x, int y) {
		visited[y][x] = true;
		q.offer(new int[] { x, y }); // 0번x, 1번y
		int minX = x, minY = y;
		int maxX = x, maxY = y;

		while (!q.isEmpty()) {
			int[] p = q.poll();
			if (minX > p[0])
				minX = p[0];
			if (maxX < p[0])
				maxX = p[0];
			if (minY > p[1])
				minY = p[1];
			if (maxY < p[1])
				maxY = p[1];
			for (int i = 0; i < 4; i++) {
				int nx = p[0] + dx[i];
				int ny = p[1] + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;

				if (!visited[ny][nx]) {
					if (board[ny][nx] != 0) {
						visited[ny][nx] = true;
						q.offer(new int[] { nx, ny });
					}
				}
			}
		}
		int diffX = maxX - minX + 1;
		int diffY = maxY - minY + 1;
		matrixs.add(new Matrix(diffX, diffY));
	}

//	출력은 y x 순
	static class Matrix implements Comparable<Matrix> {
		int x, y, size;

		public Matrix(int x, int y) {
			this.x = x;
			this.y = y;
			this.size = x * y;
		}

		@Override
		public int compareTo(Matrix o) {
			if (this.size > o.size)
				return 1;
			else if (this.size < o.size)
				return -1;
			else {
				return Integer.compare(this.y, o.y);
			}
		}

	}
}
