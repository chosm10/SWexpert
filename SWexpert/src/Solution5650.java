

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution5650 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] board;
	// 웜홀들을 번호별로 저장할 맵
	static HashMap<Integer, ArrayList<Hall>> halls;
	// 상,하,좌,우
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	// 역방향
	static int[] reverse = { 1, 0, 3, 2 };
	// 최대값
	static int ans;

	static class Hall {
		int x, y;

		public Hall(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N][N];
			halls = new HashMap<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					// 6이상이면 웜홀이므로 이미 있으면 거기다 넣고 없으면 리스트 만들고 넣음
					if (board[i][j] >= 6) {
						if (!halls.containsKey(board[i][j])) {
							halls.put(board[i][j], new ArrayList<>());
						}
						halls.get(board[i][j]).add(new Hall(j, i));
					}
				}
			}

			ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 0인 곳에서만 시작할 수 있음
					if (board[i][j] == 0) {
						// 자기 자신으로 와도 종료해야 되니까 -1로 바꿈
						board[i][j] = -1;
						for (int k = 0; k < 4; k++) {
							dfs(j, i, k, 0);
						}
						// 다 돌고 와서는 다시 원래 0으로 바꿔주기
						board[i][j] = 0;
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int x, int y, int direct, int score) {

		int nx = x;
		int ny = y;
		// 다음 방향
		int nd = direct;
		// 벽같은거 만난뒤 바뀐 점수
		int nscore = score;
		// 장애물이 아닌 동안에 계속 그방향으로 감
		while (true) {
			nx += dx[direct];
			ny += dy[direct];
			// 밖으로 나갈라하면 점수 1점 증가하고 방향 반대로
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				nscore += 1;
				nd = reverse[nd];
				break;
			}

			// -1 만나면 원래자리거나 블랙홀이므로 최대 점수랑 비교하고 종료
			if (board[ny][nx] == -1) {
				ans = ans < score ? score : ans;
				return;
			}

			// 블록을 만나면 점수 1점 증가하고 위치는 그대로 하고 방향만 바꿔줌
			if (board[ny][nx] > 0 && board[ny][nx] <= 5) {
				nd = changeDirect(board[ny][nx], nd);
				nscore += 1;
				break;
			}
			// 웜홀을 만나면 같은 번호 웜홀의 x,y로 좌표 바꿔주고 방향은 그대로
			if (board[ny][nx] > 5) {
				Hall h = halls.get(board[ny][nx]).get(0);
				// 0번이랑 좌표가 같으면 1번이 가야할곳임
				if (h.x == nx && h.y == ny) {
					h = halls.get(board[ny][nx]).get(1);
				}

				// 여기 오면 가야될 웜홀이 h임 그걸로 좌표 변경
				nx = h.x;
				ny = h.y;
				break;
			}
		}

		// 변경된 좌표, 방향, 점수로 재귀 호출
		dfs(nx, ny, nd, nscore);
	}

	// 블록에 부딪히면 방향 바꿔주기
	static int changeDirect(int block, int direct) {
		int ndriect = 0;
		switch (block) {
		case 1:
			switch (direct) {
			case 0:
				ndriect = 1;
				break;
			case 1:
				ndriect = 3;
				break;
			case 2:
				ndriect = 0;
				break;
			case 3:
				ndriect = 2;
				break;
			}
			break;
		case 2:
			switch (direct) {
			case 0:
				ndriect = 3;
				break;
			case 1:
				ndriect = 0;
				break;
			case 2:
				ndriect = 1;
				break;
			case 3:
				ndriect = 2;
				break;
			}
			break;
		case 3:
			switch (direct) {
			case 0:
				ndriect = 2;
				break;
			case 1:
				ndriect = 0;
				break;
			case 2:
				ndriect = 3;
				break;
			case 3:
				ndriect = 1;
				break;
			}
			break;
		case 4:
			switch (direct) {
			case 0:
				ndriect = 1;
				break;
			case 1:
				ndriect = 2;
				break;
			case 2:
				ndriect = 3;
				break;
			case 3:
				ndriect = 0;
				break;
			}
			break;
		case 5:
			switch (direct) {
			case 0:
				ndriect = 1;
				break;
			case 1:
				ndriect = 0;
				break;
			case 2:
				ndriect = 3;
				break;
			case 3:
				ndriect = 2;
				break;
			}
			break;
		}
		return ndriect;
	}
}
