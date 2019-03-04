import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution1873 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static char[] direct = {'^', 'v', '<', '>'};
	static char[][] board;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		int H, W, N;
		String opr = null;
		int nowX = 0, nowY = 0, d = 0;
		boolean shoot = false;
		StringBuilder result = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			board = new char[H][W];
			for (int i = 0; i < H; i++) {
				String tmp = br.readLine();
				for (int j = 0; j < W; j++) {
					board[i][j] = tmp.charAt(j);
					if (board[i][j] == '>') {
						nowX = j; nowY = i; d = 3;
					} else if (board[i][j] == '<') {
						nowX = j; nowY = i; d = 2;
					} else if (board[i][j] == 'v') {
						nowX = j; nowY = i; d = 1;
					} else if (board[i][j] == '^') {
						nowX = j; nowY = i; d = 0;
					}
				}
			}
			N = Integer.parseInt(br.readLine());

			opr = br.readLine();
			for (int i = 0; i < opr.length(); i++) {
				shoot = false;
				switch (opr.charAt(i)) {
				case 'S':
					shoot = true;
					break;
				case 'R':
					d = 3;
					break;
				case 'L':
					d = 2;
					break;
				case 'D':
					d = 1;
					break;
				case 'U':
					d = 0;
					break;
				}
				
				if (shoot) {
					int nx = nowX;
					int ny = nowY;
					while (true) {
						nx += dx[d];
						ny += dy[d];
						if (nx < 0 || nx >= W || ny < 0 || ny >= H)
							break;
						if (board[ny][nx] == '*') {
							board[ny][nx] = '.';
							break;
						} else if (board[ny][nx] == '#')
							break;
					}
				} else {
					board[nowY][nowX] = direct[d];
					int nx = nowX + dx[d];
					int ny = nowY + dy[d];
					
					if (nx < 0 || nx >= W || ny < 0 || ny >= H)
						continue;
					if (board[ny][nx] == '.') {
						board[nowY][nowX] = '.';
						nowX = nx;
						nowY = ny;
						board[nowY][nowX] = direct[d];
					}
				}
			}
			result.append("#").append(tc).append(" ");
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					result.append(board[i][j]);
				}
				result.append("\n");
			}
		}
		bw.write(result.toString());
		bw.flush();
		bw.close();
	}
}
