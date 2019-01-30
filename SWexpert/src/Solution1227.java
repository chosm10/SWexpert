
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1227 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static final int MAX = 100;
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	static int[][] board;
	static boolean[][] check;
	public static void main(String[] args) throws Exception {
		for(int t = 0; t < 10; t++) {
			int tc = Integer.parseInt(br.readLine());
			board = new int[MAX][MAX];
			check = new boolean[MAX][MAX];
			Queue<Point> q = new LinkedList<>();

			for(int i = 0; i < board.length; i++) {
				st = new StringTokenizer(br.readLine());
				String tmp = st.nextToken();
				for(int j = 0; j < board[i].length; j++) {
					board[i][j] = tmp.charAt(j) - '0';
					if(board[i][j] == 2) {
						check[i][j] = true;
						q.offer(new Point(j, i));
					}
					else if(board[i][j] == 1) {
						check[i][j] = true;
					}
				}
			}
			boolean possible = false;
			while(!q.isEmpty()) {
				Point p = q.poll();
				if(board[p.y][p.x] == 3) {
					possible = true;
					break;
				}
				for(int i = 0; i < 4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					
					if(nx < 0 || nx >= MAX || ny < 0 || ny >= MAX)
						continue;
					if(!check[ny][nx]) {
						check[ny][nx] = true;
						q.offer(new Point(nx, ny));
					}
				}
			}
			StringBuilder result = new StringBuilder("#").append(tc).append(" ");
			if(possible) {
				result.append(1);
			}
			else {
				result.append(0);
			}
			
			System.out.println(result);
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
