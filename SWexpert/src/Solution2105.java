import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution2105 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;
	static int[][] board;
	static boolean[] check;
	static int[] dx = {-1, 1, 1, -1};
	static int[] dy = {1, 1, -1, -1};
	static int maxNum;
	static int startX, startY;
	public static void dfs(int x, int y, int direct, int cnt) {
		 check[board[y][x]] = true;
		 for(int i = 0; i < 2; i++) {
			 int ndirect;
			 if(i == 1) {
				 if(direct == 3) 
					 continue;
				 ndirect = direct + 1;
			 }
			 else {
				 ndirect = direct;
			 }
			 int nx = x + dx[ndirect];
			 int ny = y + dy[ndirect];
			 if(nx < 0 || nx >= N || ny < 0 || ny >= N)
				 continue;
			 if(nx == startX && ny == startY) {
				 if(cnt > maxNum)
					 maxNum = cnt;
				 return;
			 }
			 if(check[board[ny][nx]]) // 이미 먹은 디저트 숫자면 그냥 다음 진행
				 continue;
			 dfs(nx, ny, ndirect, cnt + 1);
			 check[board[ny][nx]] = false;
		 }
	}
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			maxNum = -1; // -1이면 디저트 못먹는 경우
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					check = new boolean[101];
					startX = j; startY = i;
					dfs(j, i, 0, 1);
				}
			}
			result.append("#").append(tc).append(" ").append(maxNum).append("\n");
		}
		bw.write(result.toString());
		bw.flush();
		bw.close();
	}
}
