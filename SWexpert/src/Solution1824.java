
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1824 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C;
	static char[][] board;
	static boolean[][][][] check;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean possible;
	static int memory;
	static void go(int x, int y, int direct) {

		if (x == C)
			x = 0;
		if (x == -1)
			x = C - 1;
		if (y == R)
			y = 0;
		if (y == -1)
			y = R - 1;
		if (memory == 16)
			memory = 0;
		if (memory == -1)
			memory = 15;

		if (check[y][x][direct][memory])
			return;
		else
			check[y][x][direct][memory] = true;

		switch (board[y][x]) {
		case '<':
			go(x - 1, y, 0);
			break;
		case '>':
			go(x + 1, y, 1);
			break;
		case '^':
			go(x, y - 1, 2);
			break;
		case 'v':
			go(x, y + 1, 3);
			break;
		case '_':
			if (memory == 0) {
				go(x + 1, y, 1);
			} else {
				go(x - 1, y, 0);
			}
			break;
		case '|':
			if (memory == 0) {
				go(x, y + 1, 3);
			} else {
				go(x, y - 1, 2);
			}
			break;
		case '?':
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				go(nx, ny, i);
			}
			break;
		case '.':
			go(x + dx[direct], y + dy[direct], direct);
			break;
		case '@':
			possible = true;
			return;
		case '+':
			memory += 1;
			go(x + dx[direct], y + dy[direct], direct);
			break;
		case '-':
			memory -= 1;
			go(x + dx[direct], y + dy[direct], direct);
			break;
		default:
			memory = board[y][x] - '0';
			go(x + dx[direct], y + dy[direct], direct);
		}

	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		Queue<Point> q = new LinkedList<>();
		Point p;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			board = new char[R][C];
			check = new boolean[R][C][4][16];
			boolean checkPossible = false;
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				String tmp = st.nextToken();
				for (int j = 0; j < C; j++) {
					board[i][j] = tmp.charAt(j);
					if(board[i][j] == '@') {
						q.offer(new Point(j, i));
					}
				}
			}
			
			while(!q.isEmpty()) {
				p = q.poll();
				int x = p.x;
				int y = p.y;
				
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(nx < 0 || nx >= C || ny < 0 || ny >= R)
						continue;
					if(i == 0 || i == 1) {
						if(board[ny][nx] != '^' && board[ny][nx] != 'v') {
							checkPossible = true;
							break;
						}
					}
					else {
						if(board[ny][nx] != '<' && board[ny][nx] != '>') {
							checkPossible = true;
							break;
						}
					}
				}
				if(checkPossible)
					break;
			}
			
			possible = false;
			memory = 0;
			if(checkPossible)
				go(0, 0, 1);
			if (possible) {
				System.out.println("#" + tc + " " + "YES");
			} else {
				System.out.println("#" + tc + " " + "NO");
			}
		}
	}
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
