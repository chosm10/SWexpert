import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3282 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[][] bucket = new int[N + 1][2];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				bucket[i][0] = Integer.parseInt(st.nextToken());// 부피
				bucket[i][1] = Integer.parseInt(st.nextToken());// 가치
			}

			int[][] dp = new int[N + 1][K + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= K; j++) {
					if (j < bucket[i][0]) {
						dp[i][j] = dp[i - 1][j];
					} else {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - bucket[i][0]] + bucket[i][1]);
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(dp[N][K]).append("\n");
		}
		System.out.println(sb);
	}
}
