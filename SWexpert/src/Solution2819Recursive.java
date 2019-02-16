import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution2819Recursive {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] board;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		board = new int[4][4];
		
		for(int tc = 1; tc <= T; tc++) {
			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 4; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			set = new HashSet<String>();
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					recursive(j, i, 0, board[i][j] + "");
				}
			}
			
			result.append("#").append(tc).append(" ").append(set.size()).append("\n");
		}
		bw.write(result.toString());
		bw.flush();
		bw.close();
	}
	
	static Set<String> set;
	static void recursive(int x, int y, int depth, String number) {
		if(depth == 6) {
			set.add(number);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
				continue;
			
			recursive(nx, ny, depth + 1, number + board[ny][nx]);
		}
	}
}
