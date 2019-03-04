import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution1247Backtrack {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int hx, hy, cx, cy;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] customers = new int[N][2];
			boolean[] visited = new boolean[N];
			st = new StringTokenizer(br.readLine());
			cx = Integer.parseInt(st.nextToken());
			cy = Integer.parseInt(st.nextToken());
			hx = Integer.parseInt(st.nextToken());
			hy = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < N; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			}
			ans = Integer.MAX_VALUE;
			backtrack(customers, visited, 0, 0, cx, cy);
			result.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		bw.write(result.toString());
		bw.flush();
		bw.close();
	}
	static int ans;
	static void backtrack(int[][] customers, boolean[] visited, int idx, int dist, int lastX, int lastY) {
		if(idx == customers.length) {
			int ndist = Math.abs(lastX - hx) + Math.abs(lastY- hy);
			dist += ndist;
			if(ans > dist)
				ans = dist;
			return;
		}
		
		if(ans < dist)
			return;
		for(int i = 0; i < customers.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				int ndist = Math.abs(lastX - customers[i][0]) + Math.abs(lastY - customers[i][1]);
				backtrack(customers, visited, idx + 1, ndist, customers[i][0], customers[i][1]);
				visited[i] = false;
			}
		}
	}
}
