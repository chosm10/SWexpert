

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution5658 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			String s = br.readLine().trim();

			ArrayList<String> history = new ArrayList<>();
			// N번만 돌려보면 됨
			int divide = N / 4;
			for (int i = 0; i < N; i++) {
				s = s.charAt(s.length() - 1) + s.substring(0, s.length() - 1);
				for (int j = 0; j < 4; j++) {
					String num = s.substring(j * divide, (j + 1) * divide);
					if (!history.contains(num))
						history.add(num);
				}
			}

			Collections.sort(history);
			long ans = getDec(history.get(history.size() - K));
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static long getDec(String num) {
		long sum = 0;
		String reverse = new StringBuilder().append(num).reverse().toString();
		int mul = 1;
		for (int i = 0; i < reverse.length(); i++) {
			char c = reverse.charAt(i);
			if (c >= '0' && c <= '9') {
				sum += mul * (c - '0');
			} else {
				sum += mul * (c - 'A' + 10);
			}
			mul *= 16;
		}
		return sum;
	}
}
