import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1952 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] price;
	static int[] use;
	static int ans;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {

			price = new int[4];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < price.length; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			use = new int[13];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < use.length; i++) {
				use[i] = Integer.parseInt(st.nextToken());
			}

			ans = Integer.MAX_VALUE;
			solve(1, 0);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void solve(int depth, int pay) {
		if (depth >= use.length) {
			ans = ans > pay ? pay : ans;
			return;
		}

		if (pay >= ans)
			return;

		// 하루치로 할때
		solve(depth + 1, pay + use[depth] * price[0]);
		// 한달치로 할때
		solve(depth + 1, pay + price[1]);
		// 세달치로 할때
		solve(depth + 3, pay + price[2]);
		// 1년치로 할때
		solve(depth + 12, pay + price[3]);
	}
}
