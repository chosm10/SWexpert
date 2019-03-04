import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Solution2806 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] map;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		int N = 0;
		StringBuilder result = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			ans = 0;
			backtrack(map, 0, N);
			result.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		bw.write(result.toString());
		bw.flush();
		bw.close();
	}

	static int ans = 0;

	static void backtrack(int[][] map, int line, int N) {
		
		if (line == N) {
			ans++;
			return;
		}
		for(int i = 0; i < N; i++) {
			map[line][i] = 1;
			if(check(map, i, line)) {
				backtrack(map, line + 1, N);
			}
			map[line][i] = 0;
		}
	}

	static boolean check(int[][] map, int x, int y) {
        for(int i = y - 1; i >= 0; i--) {
        	if(map[i][x] == map[y][x])
        		return false;
        }
        int tx = x - 1; int ty = y - 1;
        while(tx >= 0 && ty >= 0) {
        	if(map[ty--][tx--] == map[y][x])
        		return false;
        }
        tx = x + 1; ty = y - 1;
        while(tx < map.length && ty >= 0) {
        	if(map[ty--][tx++] == map[y][x])
        		return false;
        }
        return true;
    }
}
