

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution2382 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, time, K;
	static ArrayList<Bug>[][] board;
	static ArrayList<Bug> bugs = new ArrayList<>();
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, -1, 1, 0, 0 };
	static int[] reverse = { 0, 2, 1, 4, 3 };

	static class Bug implements Comparable<Bug> {
		int x, y, num, direct;

		public Bug(int y, int x, int num, int direct) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.direct = direct;
		}

		@Override
		public int compareTo(Bug o) {
			// TODO Auto-generated method stub
			return o.num - this.num;
		}
	}

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			bugs.clear();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new ArrayList[N][N];

			// 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					board[i][j] = new ArrayList<>();
				}
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				Bug b = new Bug(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				board[b.y][b.x].add(b);
				bugs.add(b);
			}

			// 주어진 시간동안 돈다
			for (int t = 0; t < time; t++) {
//				모든 벌레들에 대해서 1초에 한칸씩 이동
				for (int i = 0; i < bugs.size(); i++) {
					Bug bug = bugs.get(i);

					int nx = bug.x + dx[bug.direct];
					int ny = bug.y + dy[bug.direct];

					// 끝에 도착한거는 개체수 반으로 죽고 방향은 반대가 됨
					if (nx < 1 || nx >= N - 1 || ny < 1 || ny >= N - 1) {
						bug.direct = reverse[bug.direct];
						bug.num = bug.num / 2;

//						부딪혀서 개체수 0이 되면 격리실과 리스트에서 삭제
						if (bug.num == 0) {
							board[bug.y][bug.x].remove(bug);
							bugs.remove(i);
							i--;
							continue;
						}
					}

					board[bug.y][bug.x].remove(bug);
					bug.x = nx;
					bug.y = ny;
					board[bug.y][bug.x].add(bug);
				}

				// 여기까지 하면 벌레들 한칸씩 옮기고 벽에 부딪힌거 처리한거임
				// 여기부터는 한칸에 모이는거 검사해야됨.

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (board[i][j].size() > 1) {
							Collections.sort(board[i][j]);
							Bug first = board[i][j].get(0);
							for (int k = 1; k < board[i][j].size(); k++) {
								first.num += board[i][j].get(k).num;
								bugs.remove(board[i][j].get(k));
								board[i][j].remove(k);
								k--;
							}
						}
					}
				}
			}
			int sum = 0;
			for (int i = 0; i < bugs.size(); i++) {
				sum += bugs.get(i).num;
			}
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}
}
