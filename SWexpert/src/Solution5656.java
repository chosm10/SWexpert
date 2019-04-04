
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution5656 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, X, Y;
    static int[][] board;
    static int total;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int min;
    static Queue<Point> q = new LinkedList<>();
 
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
 
        for (int tc = 1; tc <= T; tc++) {
 
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            board = new int[Y][X];
 
            total = 0;
            int[] heights = new int[X];
            boolean[] check = new boolean[X];
            for (int i = 0; i < Y; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < X; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] != 0) {
                        total++;
                        if (!check[j]) {
                            check[j] = true;
                            heights[j] = i;
                        }
                    }
                }
            }
            min = Integer.MAX_VALUE;
 
            dfs(0, 0, board, heights);
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
 
    static void dfs(int depth, int destroyed, int[][] arr, int[] heights) {
        if (depth == N) {
            int remain = total - destroyed;
            min = Math.min(min, remain);
            return;
        }
 
        for (int i = 0; i < X; i++) {
            int[][] copy = new int[Y][X];
            for (int j = 0; j < Y; j++) {
                for (int k = 0; k < X; k++) {
                    copy[j][k] = arr[j][k];
                }
            }
 
            int[] copyHeight = new int[X];
            for (int j = 0; j < X; j++) {
                copyHeight[j] = heights[j];
            }
 
            int destroy = bomb(i, heights[i], copy);
            push(copy, copyHeight);
 
            dfs(depth + 1, destroy + destroyed, copy, copyHeight);
        }
    }
    
    static int bomb(int x, int y, int[][] arr) {
        if (arr[y][x] == 0)
            return 0;
        
        q.clear();
        q.offer(new Point(x, y, arr[y][x]));
        arr[y][x] = 0;
        
        int cnt = 0;
        while(!q.isEmpty()) {
        	Point p = q.poll();
        	cnt++;
        	for(int i = 0; i < 4; i++) {
        		for(int j = 1; j < p.range; j++) {
        			int nx = p.x + dx[i] * j;
        			int ny = p.y + dy[i] * j;
        			
        			if(nx < 0 || nx >= X || ny < 0 || ny >= Y)
        				continue;
        			if(arr[ny][nx] != 0) {
        				q.offer(new Point(nx, ny, arr[ny][nx]));
        				arr[ny][nx] = 0;
        			}
        		}
        		
        	}
        }
      
        return cnt;
    }
 
    static class Point{
    	int x, y, range;

		public Point(int x, int y, int range) {
			this.x = x;
			this.y = y;
			this.range = range;
		}
    	
    }
    
    static void push(int[][] arr, int[] heights) {
        for (int i = 0; i < X; i++) {
            int height = heights[i];
            for (int h = Y - 1; h >= height; h--) {
                if (arr[h][i] != 0) {
                    int ny = h;
                    while (ny + 1 < Y) {
                        if (arr[ny + 1][i] == 0) {
                            ny += 1;
                        } else {
                            break;
                        }
                    }
                    int tmp = arr[h][i];
                    arr[h][i] = 0;
                    arr[ny][i] = tmp;
                    heights[i] = ny;
                }
            }
        }
    }
}
