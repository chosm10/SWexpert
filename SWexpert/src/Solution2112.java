import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2112 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int Y, X, K;
	static int[][] board;
	static int ans;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			Y = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new int[Y][X];

			for (int i = 0; i < Y; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < X; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = Integer.MAX_VALUE;
			solve(0, 0);

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	// 깊이, 색칠한 막의 개수
	static void solve(int depth, int cnt) {

		// 현재 위치에서 전부다 K회로 통과하는지 검사 하고 통과하면 여태까지 최소값보다 작으면 바꿔
		boolean flag = true;
		for (int i = 0; i < X; i++) {
			if (!isOK(i)) {
				flag = false;
				break;
			}
		}

		// 다 통과했으면
		if (flag) {
			ans = Math.min(ans, cnt);
			return;
		}

		if (depth == Y) {
			return;
		}

		int[] tmp = new int[X];
		for (int i = 0; i < X; i++) {
			tmp[i] = board[depth][i];
		}

		// 그냥 가거나 A로 색칠하고 가거나 B로 색칠하고 감
		if (cnt + 1 < ans) {
			// 그냥 가
			solve(depth + 1, cnt);
			// A로 색칠
			change(depth, 0);
			solve(depth + 1, cnt + 1);

			// B로 색칠
			change(depth, 1);
			solve(depth + 1, cnt + 1);
			// 나오면서 원래대로 복구
			back(tmp, depth);
		}
	}
	
	//원래 막 상태로 되돌리기
	static void back(int[] arr, int idx) {
		for (int i = 0; i < X; i++) {
			board[idx][i] = arr[i];
		}
	}
	
	//한 열이 성능테스트 통과했는지
	static boolean isOK(int index) {
		int cnt = 1;
		for (int i = 0; i < Y - 1; i++) {
			if (board[i][index] == board[i + 1][index]) {
				cnt++;
			} else {
				cnt = 1;
			}
			if (cnt == K)
				return true;
		}
		return false;
	}
	
	//한 행을 A나 B로 바꾸기
	static void change(int index, int num) {
		for (int i = 0; i < X; i++) {
			board[index][i] = num;
		}
	}
}
