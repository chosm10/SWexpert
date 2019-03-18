import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution7250 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int X, Y, K;
	static char[][] board;
	static Queue<Player> q = new LinkedList<>();
	static Queue<Player> fire = new LinkedList<>();
	static int[] villon;
	static int[] scat;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] end;

	static class Player {
		int x, y, time;

		public Player(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Player(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

	}

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			Y = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new char[Y][X];
			villon = new int[2];
			scat = new int[2];
			end = new int[2];
			fire.clear();

			for (int i = 0; i < Y; i++) {
				String tmp = br.readLine().trim();
				for (int j = 0; j < X; j++) {
					board[i][j] = tmp.charAt(j);
					switch (board[i][j]) {
					case 'S':
						scat[0] = j;
						scat[1] = i;
						break;
					case 'V':
						villon[0] = j;
						villon[1] = i;
						break;
					case 'E':
						end[0] = j;
						end[1] = i;
						break;
					case 'F':
						fire.offer(new Player(j, i));
						break;
					}
				}
			}

			int villonCnt = villonBfs(villon[0], villon[1]);
			int scatCnt = scatBfs(scat[0], scat[1]);

			sb.append("#").append(tc).append(" ").append(villonCnt <= scatCnt ? -1 : scatCnt).append("\n");
		}
		System.out.println(sb);
	}

	static int villonBfs(int x, int y) {

		boolean[][] visited = new boolean[Y][X];
		q.clear();
		q.offer(new Player(x, y));
		visited[y][x] = true;

		int cnt = 0;
		while (!q.isEmpty()) {

			int qsize = q.size();
			for (int i = 0; i < qsize; i++) {
				Player p = q.poll();
				if (p.x == end[0] && p.y == end[1]) {
					return cnt;
				}
				for (int j = 0; j < 4; j++) {
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];

					if (isRange(nx, ny))
						continue;

					if (board[ny][nx] != 'X' && board[ny][nx] != 'W') {
						if (!visited[ny][nx]) {
							visited[ny][nx] = true;
							q.offer(new Player(nx, ny));
						}
					}
				}
			}
			cnt++;
		}

		return Integer.MAX_VALUE;
	}

	static int scatBfs(int x, int y) {
		boolean[][] visited = new boolean[Y][X];
		boolean[][] fireCheck = new boolean[Y][X];
		q.clear();
		q.offer(new Player(x, y, K));
		visited[y][x] = true;

		int cnt = 0;
		while (!q.isEmpty()) {

			doFire(fireCheck);
			int qsize = q.size();
			for (int i = 0; i < qsize; i++) {
				Player s = q.poll();

				for (int j = 0; j < 4; j++) {
					int nx = s.x + dx[j];
					int ny = s.y + dy[j];

					if (isRange(nx, ny))
						continue;

					if (board[ny][nx] == 'A') {
						if (!visited[ny][nx]) {
							visited[ny][nx] = true;
							q.offer(new Player(nx, ny, K));
						}
					} else if (board[ny][nx] == 'W') {
						if (s.time > 0) {
							q.offer(new Player(nx, ny, s.time - 1));
						}
					} else if (board[ny][nx] == 'E') {
						return cnt + 1;
					}
				}
			}
			cnt++;
		}

		return -1;
	}

	static void doFire(boolean[][] visited) {
		int qsize = fire.size();
		for (int i = 0; i < qsize; i++) {
			Player f = fire.poll();

			for (int j = 0; j < 4; j++) {
				int nx = f.x + dx[j];
				int ny = f.y + dy[j];

				if (isRange(nx, ny))
					continue;

				if (board[ny][nx] == 'A') {
					if (!visited[ny][nx]) {
						visited[ny][nx] = true;
						board[ny][nx] = 'F';
						fire.offer(new Player(nx, ny));
					}
				}
			}
		}
	}

	static boolean isRange(int x, int y) {
		if (x < 0 || x >= X || y < 0 || y >= Y)
			return true;
		return false;
	}
}
