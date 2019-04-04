

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4014 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, X;
	static int[][] board;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			board = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (isOk(board[i])) {
					cnt += 1;
				}
				int[] copy = new int[N];
				for (int j = 0; j < N; j++) {
					copy[j] = board[j][i];
				}
				if (isOk(copy)) {
					cnt += 1;
				}
			}
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	static boolean isOk(int[] arr) {
		int idx = 0;
		boolean[] check = new boolean[arr.length];
		while (idx < N) {
			if (idx + 1 < N) {
				if (Math.abs(arr[idx] - arr[idx + 1]) > 1)
					return false;
				if (arr[idx] == arr[idx + 1]) {
					idx += 1;
				} else if (arr[idx + 1] - arr[idx] == 1) {
					// 가다가 높은곳이 나온경우
					// 현재위치를 포함해 뒤로 X만큼이 지형이 있고, 높이가 같으면 idx + 1
					if (idx - X + 1 >= 0) {
						for (int i = idx - X + 1; i < idx; i++) {
							if (check[i])
								return false;
							if (arr[i] != arr[i + 1])
								return false;
						}
						idx += 1;
					} else {
						return false;
					}
				} else if (arr[idx] - arr[idx + 1] == 1) {
					// 가다가 낮은 곳이 나온 경우
					// 다음 위치를 포함해 X만큼 지형이 있고, 높이가 같으면 idx + 1 + X
					if (idx + X < N) {
						for (int i = idx + 1; i < idx + X; i++) {
							if (arr[i] != arr[i + 1])
								return false;
							check[i] = true;
						}
						check[idx + X] = true;
						idx += X;
					} else {
						return false;
					}
				}
			}
			if (idx == N - 1)
				break;
		}
		return true;
	}
}
