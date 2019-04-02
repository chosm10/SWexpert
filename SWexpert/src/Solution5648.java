

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5648 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] board = new int[4001][4001];
	static boolean[][] check = new boolean[4001][4001];
	static Queue<Atom> q = new LinkedList<>();
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	static class Atom {
		int x, y;
		int direct, energy;

		public Atom(int x, int y, int direct, int energy) {
			this.x = x;
			this.y = y;
			this.direct = direct;
			this.energy = energy;
		}
	}

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			
			// 값 받기
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				Atom a = new Atom((Integer.parseInt(st.nextToken()) + 1000) * 2,
						(Integer.parseInt(st.nextToken()) + 1000) * 2, Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
				board[a.y][a.x] = 1;
				q.offer(a);
				cnt++;
			}

//			4000초 동안 실행
			int time = 4000;
			int total = 0;
			while (time > 0) {

				int qsize = q.size();
				for (int i = 0; i < qsize; i++) {
					Atom a = q.poll();

					int nx = a.x + dx[a.direct];
					int ny = a.y + dy[a.direct];
					// 현재 위치를 빼주고 밖으로 나가는거 체크해야 밖으로 나간걸 없어지게 할 수 있음
					board[a.y][a.x] -= 1;
					if (nx < 0 || nx >= 4001 || ny < 0 || ny >= 4001)
						continue;

					board[ny][nx] += 1;
					if (board[ny][nx] >= 2)
						check[ny][nx] = true;
					a.x = nx;
					a.y = ny;
					q.offer(a);
				}

				qsize = q.size();
				for (int i = 0; i < qsize; i++) {
					Atom a = q.poll();

					if (check[a.y][a.x]) {
						if (board[a.y][a.x] == 1) {
							check[a.y][a.x] = false;
						}
						board[a.y][a.x] -= 1;
						total += a.energy;
						cnt -= 1;
					} else {
						q.offer(a);
					}
				}
				if (cnt == 0)
					break;
				time -= 1;
			}
			while (!q.isEmpty()) {
				Atom a = q.poll();
				board[a.y][a.x] = 0;
			}
			sb.append("#").append(tc).append(" ").append(total).append("\n");
		}
		System.out.println(sb);
	}
}
