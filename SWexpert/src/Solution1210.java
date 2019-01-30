
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution1210 {
	static boolean check[][];
	static int[] dx = { -1, 1, 0 };
	static int[] dy = { 0, 0, -1 };
	static final int MAX = 100;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			check = new boolean[MAX][MAX];
			int t = Integer.parseInt(br.readLine());
			int startX = 0;
			for (int i = 0; i < MAX; i++) {
				String[] tmp = br.readLine().split(" ");
				for (int j = 0; j < MAX; j++) {
					if(tmp[j].equals("0")) {
						check[i][j] = true;
					}
					else if(tmp[j].equals("2")) {
						startX = j;
					}
				}
			}
			// 거꾸로생각해보기!!!
			int x = startX;
			int y = MAX - 1;

			while (y != 0) {
				check[y][x] = true;
				if (x - 1 >= 0 && !check[y][x - 1])
					x--;
				else if (x + 1 < MAX && !check[y][x + 1])
					x++;
				else
					y--;
				//						check[y][x] = true;
				//						for (int j = 0; j < 3; j++) {
				//							int nx = x + dx[j];
				//							int ny = y + dy[j];
				//							if (nx < 0 || nx >= MAX || ny < 0 || ny >= MAX)
				//								continue;
				//							if (board[ny][nx] != 0) {
				//								if (!check[ny][nx]) {
				//									x = nx;
				//									y = ny;
				//									break;
				//								}
				//							}
				//						}
			}
			System.out.println("#" + t + " " + x);


		}
	}
}
