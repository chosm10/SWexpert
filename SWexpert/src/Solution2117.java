import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution2117 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, K;
	static int[][] board;
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			board = new int[N][N];
			
			int home = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] == 1) {
						home++;
					}
				}
			}
			
			K = getK(home * M);
			
		}
	}
	
	static void bfs(int x, int y) {
		boolean[][] visited = new boolean[N][N];
		visited[y][x] = true;
		q.offer(new Point(x, y));
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int qsize = q.size();
			//cnt가 최대 K가 되면 중지
			if(cnt == K) {
				q.clear();
				break;
			}
			for(int i = 0; i < qsize; i++) {
				Point p = q.poll();
				
				for(int j = 0; j < 4; j++) {
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];
					if(nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					
					if(!visited[ny][nx]) {
						visited[ny][nx] = true;
						q.offer(new Point(nx, ny));
					}
				}
			}
			cnt++;
		}
	}
	
	static int getK(int num) {
		int i = 1;
		while(true) {
			int pay = getPay(i);
			if(pay > num)
				return i - 1;
			i++;
		}
	}
	
	static int getPay(int k) {
		return (int)(Math.pow(k, 2) + Math.pow(k - 1, 2));
	}
}
