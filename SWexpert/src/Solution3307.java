
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3307 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[] arr;
	static int[] dp;
	public static void main(String[] args) throws Exception{
		int T;
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			arr = new int[N + 1];
			dp = new int[N + 1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken()); 
			}
			
			dp[1] = 1;
			for(int i = 2; i <= N; i++) {
				for(int j = 1; j < i; j++) {
					if(arr[i] > arr[j]) {
						if(dp[i] < dp[j]) {
							dp[i] = dp[j];
						}
					}
				}
				dp[i] += 1;
			}
			
			int max = dp[1];
			
			for(int i = 2; i <= N; i++) {
				if(max < dp[i]) {
					max = dp[i];
				}
			}
			
			System.out.println("#" + tc + " " + max);
		}
	}
}
