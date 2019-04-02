import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5644 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int time, bcCnt;
	static int[] dx = {0, 0, 1, 0, -1};
	static int[] dy = {0, -1, 0, 1, 0};
	static ArrayList<Integer>[][] board;
	static int[] trackA;
	static int[] trackB;
	static ArrayList<BC> bcs;
	
	public static void main(String[] args)  throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			time = Integer.parseInt(st.nextToken());
			bcCnt = Integer.parseInt(st.nextToken());
			board = new ArrayList[11][11];
			for(int i = 1; i < board.length; i++) {
				for(int j = 1; j < board.length; j++) {
					board[i][j] = new ArrayList<>();
				}
			}
			
			trackA = new int[time + 1];
			trackB = new int[time + 1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < trackA.length; i++) {
				trackA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < trackB.length; i++) {
				trackB[i] = Integer.parseInt(st.nextToken());
			}
			
			bcs = new ArrayList<>();
			for(int i = 0; i < bcCnt; i++) {
				st = new StringTokenizer(br.readLine());
				bcs.add(new BC(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			Collections.sort(bcs);
			
			for(int i = 0; i < bcs.size(); i++) {
				bfs(bcs.get(i));
			}
			
			for(int i = 1; i <= 10; i++) {
				for(int j = 1; j <= 10; j++) {
					board[i][j].add(0);
				}
			}
			
			for(int i = 1; i <= 10; i++) {
				for(int j = 1; j <= 10; j++) {
					System.out.print(board[i][j].get(0) + " ");
				}
				System.out.println();
			}
			
			int sum = 0;
			int aX = 1, aY = 1, bX = 10, bY = 10;
			int aFlag = 0;
			int bFlag = 0;
			for(int i = 0; i < trackA.length; i++) {
				int aDirect = trackA[i];
				aX += dx[aDirect];
				aY += dy[aDirect];
				aFlag = board[aY][aX].get(0);
				
				int bDirect = trackB[i];
				bX += dx[bDirect];
				bY += dy[bDirect];
				bFlag = board[bY][bX].get(0);
				
				if(aFlag == 0) {
					sum += bFlag;
				} else {
					if(bFlag == 0) {
						sum += aFlag;
					} else {
						if(aFlag == bFlag) {
							sum += aFlag;
							sum += board[aY][aX].get(1) > board[bY][bX].get(1) ? board[aY][aX].get(1) : board[bY][bX].get(1);
						} else {
							sum += aFlag + bFlag;
						}
					}
				}
				
				
				
				
//				System.out.println("a: " + aFlag + " " + "b: " + bFlag);
//				if(aFlag == bFlag) {
//					if(aFlag != 0) {
//						sum += board[bY][bX].get(1);
//					}
//				} else {
//					sum += bFlag;
//				}
				System.out.println(sum);
				System.out.println("-------------");
			}
			
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}
	
	static Queue<Point> q = new LinkedList<>();
	static void bfs(BC b) {
		int x = b.x;
		int y = b.y;
		int range = b.range;
		
		q.clear();
		boolean[][] visited = new boolean[11][11];
		visited[y][x] = true;
		q.offer(new Point(x, y));
		board[y][x].add(b.power);
		
		int cnt = 0;
		while(!q.isEmpty()) {
			
			if(cnt == range)
				break;
			int qsize = q.size();
			for(int i = 0; i < qsize; i++) {
				Point p = q.poll();
				
				for(int j = 1; j <= 4; j++) {
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];
					
					if(outRange(nx, ny))
						continue;
					
					if(!visited[ny][nx]) {
						visited[ny][nx] = true;
						board[ny][nx].add(b.power);
						q.offer(new Point(nx, ny));
					}
				}
			}
			cnt++;
		}
		
	}
	
	static boolean outRange(int x, int y) {
		if(x < 1 || x >= 11 || y < 1 || y >= 11)
			return true;
		return false;
	}
	
	static class BC implements Comparable<BC>{
		int x, y, range, power;

		public BC(int x, int y, int range, int power) {
			this.x = x;
			this.y = y;
			this.range = range;
			this.power = power;
		}

		@Override
		public int compareTo(BC o) {
			return o.power - this.power;
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
