
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution2819 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] board;
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			board = new int[4][4];
			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 4; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			HashSet<String> set = new HashSet<>();
			Queue<Point> q = new LinkedList<>();
			
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					q.offer(new Point(j, i, 0, String.valueOf(board[i][j])));
			
					while(!q.isEmpty()) {
						Point p = q.poll();
						if(p.cnt == 6) {
							set.add(p.s);
						}
						else if(p.cnt < 6) {
							for(int k = 0; k < 4; k++) {
								int nx = p.x + dx[k];
								int ny = p.y + dy[k];
								
								if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
									continue;
								
								q.offer(new Point(nx, ny, p.cnt + 1, p.s + String.valueOf(board[ny][nx])));
							}
						}
					}
				}
			}
			int result = set.size();
			System.out.println(new StringBuilder("#").append(tc).append(" ").append(result));
		}
	}

	static class Point {
		int x, y, cnt;
		String s;
		public Point(int x, int y, int cnt, String s) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.s = s;
		}
	}
}
