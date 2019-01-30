
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//가지치기 할때는 우선순위큐
public class Solution1249 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] board;
	static boolean[][] check;
	static int N;
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	static PriorityQueue<Point> q;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			check = new boolean[N][N];

			for(int i = 0; i < N; i++) {
				String tmp = br.readLine();
				for(int j = 0; j < N; j++) {
					board[i][j] = tmp.charAt(j) - '0';
				}
			}
			q = new PriorityQueue<>();
			q.offer(new Point(0, 0, 0));
			check[0][0] = true;
			int result = 0;
			while(!q.isEmpty()) {
				Point p = q.poll();
				if(p.x == N - 1 && p.y == N - 1) {
					result = p.time;
					break;
				}
				for(int i = 0; i < 4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					if(!check[ny][nx]) {
						check[ny][nx] = true;
						q.offer(new Point(nx, ny, p.time + board[ny][nx]));
					}
				}
			}
			
			System.out.println(new StringBuilder("#").append(tc).append(" ").append(result));
		}
		br.close();
	}
	static class Point implements Comparable<Point>{
		int x, y, time;
		public Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
		@Override
		public int compareTo(Point o) {
			if(this.time > o.time) 
				return 1;
			else if(this.time == o.time)
				return 0;
			else
				return -1;
		}
	}
}
