import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution5653 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int Y, X, K;
	static int[][] board;
	static final int MAX = 300;
	static Queue<Cell> q = new LinkedList<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	// sleep이 매턴 증가하다가 0되면 번식하고, life랑 같아지면 죽어
	static class Cell {
		int x, y, sleep, life;

		public Cell(int x, int y, int life) {
			super();
			this.x = x;
			this.y = y;
			this.life = life;
			this.sleep = -life;
		}

	}

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {

			q.clear();
			st = new StringTokenizer(br.readLine());
			Y = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new int[MAX * 2 + Y][MAX * 2 + X];

			for (int i = MAX; i < MAX + Y; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = MAX; j < MAX + X; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] != 0) {
						q.offer(new Cell(j, i, board[i][j]));
					}
				}
			}

			HashMap<String, Cell> map = new HashMap<>();
			Set<String> keys;
			for (int time = 0; time < K; time++) {

				// 매 시간마다 중복체크하는거니까 중복처리할 맵은 계속 초기화
				map.clear();
				int qsize = q.size();
				for (int i = 0; i < qsize; i++) {
					Cell c = q.poll();

					// 번식
					if (c.sleep == 0) {
						for (int j = 0; j < 4; j++) {
							int nx = c.x + dx[j];
							int ny = c.y + dy[j];

							if (board[ny][nx] == 0) {
								// 중복체크할 키 만들기
								StringBuilder s = new StringBuilder();
								s.append("x").append(nx).append("y").append(ny);
								String key = s.toString();

								if (map.containsKey(key)) {
									// 이미 다른놈이 넣었는데 그놈보다 내꺼 life가 크면 life 큰걸로 교체
									if (map.get(key).life < c.life) {
										map.get(key).life = c.life;
									}
								} else {
									// 아직 그곳에 한놈도 안넣었으면 그냥 넣어
									map.put(key, new Cell(nx, ny, c.life));
								}
							}
						}
					}
					c.sleep++;
					// sleep과 life가 같으면 죽은거니까 넣지마
					if (c.sleep < c.life) {
						q.offer(c);
					}
				}

				// 자리 중복체크 된거 한번에 넣어주기
				keys = map.keySet();
				for (String key : keys) {
					Cell added = map.get(key);
					board[added.y][added.x] = added.life;
					q.offer(added);
				}
			}

			// 각 단계마다 죽은건 안넣으니 마지막에 큐에 남아있는 놈들이 비활성+활성이다.
			sb.append("#").append(tc).append(" ").append(q.size()).append("\n");
		}
		System.out.println(sb);
	}
}
