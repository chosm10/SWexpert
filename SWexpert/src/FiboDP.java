import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FiboDP {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(getArr(arr, N, M));
 	}
	
	static int getApart(int N) {
		int[][] dp = new int[N + 1][2];
		dp[1][0] = 1;
		dp[1][1] = 1;
		for(int i = 2; i <= N; i++) {
			dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
			dp[i][1] = dp[i - 1][0];
		}
		
		return dp[N][0] + dp[N][1];
	}
	
	
	static int getBar(int N) {
		int[] dp = new int[N + 1];
		dp[0] = 1;
		dp[1] = 2;
		for(int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] * 2 + dp[i - 2];
		}
		
		return dp[N];
	}
	
	static int getArr(int[][] arr, int n, int m) {
		for(int i = 1; i < m; i++) {
			arr[0][i] = arr[0][i] + arr[0][i - 1];
		}
		for(int i = 1; i < n; i++) {
			arr[i][0] = arr[i][0] + arr[i - 1][0];
		}
		for(int i = 1; i < n; i++) {
			for(int j = 1; j < m; j++) {
				arr[i][j] = Math.max(Math.max(arr[i - 1][j - 1], arr[i][j - 1]), arr[i - 1][j]) + arr[i][j];
			}
		}
		
		return arr[n - 1][m - 1];
	}
}
